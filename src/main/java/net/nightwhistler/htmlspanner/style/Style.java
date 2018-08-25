package net.nightwhistler.htmlspanner.style;

import android.widget.TextView;

import net.nightwhistler.htmlspanner.FontFamily;

/**
 * CSS Style object.
 *
 * A Style is immutable: using a setter creates a new Style object with the
 * changed setings.
 */
public class Style {

    public static enum TextAlignment { LEFT, CENTER, RIGHT, JUSTIFY };
    public static enum FontWeight {  NORMAL, BOLD }
    public static enum FontStyle { NORMAL, ITALIC }
    public static enum DisplayStyle { BLOCK, INLINE };
    public static enum BorderStyle { SOLID, DASHED, DOTTED, DOUBLE }

    private FontFamily fontFamily;
    private TextAlignment textAlignment;
    private StyleValue fontSize;

    private FontWeight fontWeight;
    private FontStyle fontStyle;

    private Integer color;
    private Integer backgroundColor;
    private Integer borderColor;

    private DisplayStyle displayStyle;
    private BorderStyle borderStyle;
    private StyleValue borderWidth;

    private StyleValue textIndent;

    private StyleValue marginTop;
    private StyleValue marginBottom;
    private StyleValue marginLeft;
    private StyleValue marginRight;
    private TextView textView;


    public Style() {
        fontFamily = null;
        textAlignment = null;
        fontSize = null;
        fontWeight = null;
        fontStyle = null;
        color = null;
        backgroundColor = null;
        displayStyle = null;
        marginBottom = null;
        textIndent = null;
        marginTop = null;
        marginLeft = null;
        marginRight = null;
        textView = null;
        borderColor = null;
        borderStyle = null;
        borderWidth = null;
    }

    public Style(FontFamily family, TextAlignment textAlignment, StyleValue fontSize,
                 FontWeight fontWeight, FontStyle fontStyle, Integer color,
                 Integer backgroundColor, DisplayStyle displayStyle, StyleValue marginTop,
                 StyleValue marginBottom, StyleValue marginLeft, StyleValue marginRight,
                 StyleValue textIndent, Integer borderColor, BorderStyle borderStyle,
                 StyleValue borderWidth, TextView textView) {
        this.fontFamily = family;
        this.textAlignment = textAlignment;
        this.fontSize = fontSize;

        this.fontWeight = fontWeight;
        this.fontStyle = fontStyle;
        this.color = color;
        this.backgroundColor = backgroundColor;
        this.displayStyle = displayStyle;
        this.marginBottom = marginBottom;
        this.textIndent = textIndent;
        this.marginTop = marginTop;
        this.marginLeft = marginLeft;
        this.marginRight = marginRight;

        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
        this.borderStyle = borderStyle;
        this.textView = textView;
    }

    public Style setFontFamily(FontFamily fontFamily) {
        this.fontFamily = fontFamily;
        return this;
    }

    public Style setTextAlignment(TextAlignment textAlignment) {
        this.textAlignment = textAlignment;
        return this;
    }

    public Style setFontSize(StyleValue fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public Style setFontWeight(FontWeight fontWeight) {
        this.fontWeight = fontWeight;
        return this;
    }

    public Style setFontStyle(FontStyle fontStyle) {
        this.fontStyle = fontStyle;
        return this;
    }

    public Style setColor(Integer color) {
        this.color = color;
        return this;
    }

    public Style setBackgroundColor(Integer backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public Style setBorderColor(Integer borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public Style setDisplayStyle(DisplayStyle displayStyle) {
        this.displayStyle = displayStyle;
        return this;
    }

    public Style setBorderStyle(BorderStyle borderStyle) {
        this.borderStyle = borderStyle;
        return this;
    }

    public Style setBorderWidth(StyleValue borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public Style setTextIndent(StyleValue textIndent) {
        this.textIndent = textIndent;
        return this;
    }

    public Style setMarginTop(StyleValue marginTop) {
        this.marginTop = marginTop;
        return this;
    }

    public Style setMarginBottom(StyleValue marginBottom) {
        this.marginBottom = marginBottom;
        return this;
    }

    public Style setMarginLeft(StyleValue marginLeft) {
        this.marginLeft = marginLeft;
        return this;
    }

    public Style setMarginRight(StyleValue marginRight) {
        this.marginRight = marginRight;
        return this;
    }

    public Style setTextView(TextView textView) {
        this.textView = textView;
        return this;
    }

    public Integer getBackgroundColor() {
        return this.backgroundColor;
    }

    public FontFamily getFontFamily() {
        return this.fontFamily;
    }

    public TextAlignment getTextAlignment() {
        return textAlignment;
    }

    public StyleValue getFontSize() {
        return this.fontSize;
    }

    public FontWeight getFontWeight() {
        return fontWeight;
    }

    public FontStyle getFontStyle() {
        return fontStyle;
    }

    public Integer getColor() {
        return this.color;
    }

    public DisplayStyle getDisplayStyle() {
        return this.displayStyle;
    }

    public StyleValue getMarginBottom() {
        return this.marginBottom;
    }

    public StyleValue getMarginTop() {
        return this.marginTop;
    }

    public StyleValue getMarginLeft() {
        return this.marginLeft;
    }

    public StyleValue getMarginRight() {
        return this.marginRight;
    }

    public StyleValue getTextIndent() {
        return this.textIndent;
    }

    public Integer getBorderColor() {
        return this.borderColor;
    }

    public BorderStyle getBorderStyle() {
        return this.borderStyle;
    }

    public StyleValue getBorderWidth() {
        return this.borderWidth;
    }

    public TextView getTextView() {
        return textView;
    }

    public String toString() {

        StringBuilder result = new StringBuilder( "{\n" );

        if ( fontFamily != null  ) {
            result.append( "  font-family: " + fontFamily.getName() + "\n");
        }

        if ( textAlignment != null ) {
            result.append( "  text-alignment: " + textAlignment + "\n");
        }

        if ( fontSize != null ) {
            result.append( "  font-size: " + fontSize + "\n");
        }

        if ( fontWeight != null ) {
            result.append( "  font-weight: " + fontWeight + "\n" );
        }

        if ( fontStyle != null ) {
            result.append( "  font-style: " + fontStyle + "\n" );
        }

        if ( color != null ) {
            result.append("  color: " + color + "\n");
        }

        if ( backgroundColor != null ) {
            result.append("  background-color: " + backgroundColor + "\n");
        }

        if ( displayStyle != null ) {
            result.append("  display: " + displayStyle + "\n");
        }

        if ( marginTop != null ) {
            result.append("  margin-top: " + marginTop + "\n" );
        }

        if ( marginBottom != null ) {
            result.append("  margin-bottom: " + marginBottom + "\n" );
        }

        if ( marginLeft != null ) {
            result.append("  margin-left: " + marginLeft + "\n" );
        }

        if ( marginRight != null ) {
            result.append("  margin-right: " + marginRight + "\n" );
        }

        if ( textIndent != null ) {
            result.append("  text-indent: " + textIndent + "\n" );
        }

        if ( borderStyle != null ) {
            result.append("  border-style: " + borderStyle + "\n" );
        }

        if ( borderColor != null ) {
            result.append("  border-color: " + borderColor + "\n" );
        }

        if ( borderWidth != null ) {
            result.append("  border-style: " + borderWidth + "\n" );
        }

        result.append("}\n");

        return result.toString();
    }

}
