package net.nightwhistler.htmlspanner.handlers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.DynamicDrawableSpan;
import android.util.Base64;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.nightwhistler.htmlspanner.SpanStack;
import net.nightwhistler.htmlspanner.TagNodeHandler;
import net.nightwhistler.htmlspanner.spans.DynamicImageSpan;
import net.nightwhistler.htmlspanner.util.PicassoTransformImage;

import org.htmlcleaner.TagNode;

import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Dmitry on 16.07.2015.
 */
public class PicassoImageHandler extends TagNodeHandler {

    final Picasso picasso;

    int maxWidth = -1;

    int alignment = DynamicImageSpan.ALIGN_BOTTOM;

    public PicassoImageHandler() {
        this.picasso = Picasso.get();
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public PicassoImageHandler setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
        return this;
    }


    public int getAlignment() {
        return alignment;
    }

    /**
     * @param alignment one of {@link DynamicDrawableSpan#ALIGN_BOTTOM} or
     *                  {@link DynamicDrawableSpan#ALIGN_BASELINE}.
     */
    public PicassoImageHandler setAlignment(int alignment) {
        this.alignment = alignment;
        return this;
    }

    @Override
    public void handleTagNode(TagNode tagNode, final SpannableStringBuilder builder, final int start, int end, SpanStack stack) {
        final TextView textView = getSpanner().getTextView();
        final int imageCrop = getSpanner().getImageCrop();
        if (textView != null && picasso != null) {
            builder.append("￼");
            float density = textView.getResources().getDisplayMetrics().density;
            int width = (int) (parseDimen(tagNode.getAttributeByName("width"), -1) * density);
            int height = (int) (parseDimen(tagNode.getAttributeByName("height"), -1) * density);
            Drawable drawable = null;
            if (imageCrop > 0) {
                drawable = textView.getResources().getDrawable(imageCrop);
            } else {
                drawable = new ColorDrawable(textView.getResources().getColor(android.R.color.darker_gray));
            }
            int textSize = (int) (textView.getTextSize() * 1.25);
            if (width > calculateMaxWidth(textView)) {
                width = calculateMaxWidth(textView);
            }
            if (width < 0) {
                width = textSize;
                height = textSize;
            }
            drawable.setBounds(0, 0, width, height);
            final DynamicImageSpan imageSpan = new DynamicImageSpan(drawable, alignment);
            stack.pushSpan(imageSpan, start, builder.length());
            new DynamicImageSpanAsync(start, builder.length(), textView).execute(tagNode);
        } else {
            ImageHandler imageHandler = new ImageHandler();
            imageHandler.handleTagNode(tagNode, builder, start, end, stack);
        }
    }

    private Drawable getDrawable(TextView textView, final Bitmap bitmap) {
        if (bitmap != null) {
            Drawable drawable = new BitmapDrawable(textView.getResources(), bitmap);
            drawable.setBounds(0, 0, bitmap.getWidth() - 1, bitmap.getHeight() - 1);
            return drawable;
        } else {
            return new ColorDrawable(textView.getResources().getColor(android.R.color.transparent));
        }
    }

    public int calculateMaxWidth(TextView textView) {
        if (maxWidth < 0) {
            int loc[] = new int[2];
            textView.getLocationOnScreen(loc);
            int maxWidth = textView.getResources().getDisplayMetrics().widthPixels
                    - loc[0];
            int textViewWidth = textView.getWidth() - textView.getPaddingLeft() - textView.getPaddingRight();
            if (textViewWidth > 0) {
                maxWidth = Math.min(maxWidth, textViewWidth);
            }
            return maxWidth;
        }
        return maxWidth;
    }

    int parseDimen(String value, int defaultValue) {
        if (value == null) return defaultValue;
        try {
            return Integer.parseInt(value.replace("&quot;", ""));
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    private class DynamicImageSpanAsync extends AsyncTask<TagNode, Void, Bitmap> {

        final int start;
        final int length;
        final WeakReference<TextView> textView;

        private DynamicImageSpanAsync(int start, int length, TextView textView) {
            this.start = start;
            this.length = length;
            this.textView = new WeakReference<>(textView);
        }

        @Override
        protected Bitmap doInBackground(final TagNode... meh) {
            try {
                if (meh.length > 0) {
                    TagNode tag = meh[0];
                    String src = Html.fromHtml(tag.getAttributeByName("src")).toString().replace("\"", "");
                    TextView textView;
                    if (src != null && (textView = this.textView.get()) != null) {
                        int width = parseDimen(tag.getAttributeByName("width"), -1);
                        int height = parseDimen(tag.getAttributeByName("height"), -1);
                        PicassoTransformImage transformImage = new PicassoTransformImage(width, height, calculateMaxWidth(textView), src.hashCode() + "", textView.getResources().getDisplayMetrics().density);
                        if (!src.contains("base64,")) {
                            URL url = null;
                            try {
                                url = new URL(src);
                            } catch (MalformedURLException ex) {
                                if (getSpanner().getBaseDomain() != null) {
                                    url = new URL(getSpanner().getBaseDomain() + "/" + src);
                                }
                            }
                            if (url != null)
                                return picasso.load(url.toString()).transform(transformImage).get();
                        } else {
                            byte[] decodedString = Base64.decode(src.substring(src.indexOf("base64,") + 7), Base64.DEFAULT);
                            return transformImage.transform(BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length));
                        }
                    }
                }
            } catch (Exception e) {
                return null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(final Bitmap bitmap) {
            TextView textView;
            if((textView = this.textView.get()) != null) {
                CharSequence sequence = textView.getText();
                if(sequence instanceof SpannableString) {
                    SpannableString spannableString = (SpannableString) sequence;
                    DynamicImageSpan[] spans = spannableString.getSpans(start, length, DynamicImageSpan.class);
                    if(spans.length > 0) {
                        for (DynamicImageSpan span : spans) {
                            spannableString.removeSpan(span);
                        }
                        Drawable newImage = getDrawable(textView, bitmap);
                        DynamicImageSpan span = new DynamicImageSpan(newImage);
                        spannableString.setSpan(span, start, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }
            }
        }
    }

}
