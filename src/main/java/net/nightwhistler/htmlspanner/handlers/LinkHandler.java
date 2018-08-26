/*
 * Copyright (C) 2011 Alex Kuiper <http://www.nightwhistler.net>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.nightwhistler.htmlspanner.handlers;


import net.nightwhistler.htmlspanner.SpanStack;
import net.nightwhistler.htmlspanner.TagNodeHandler;

import org.htmlcleaner.TagNode;

import android.text.SpannableStringBuilder;
import android.text.style.URLSpan;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Creates clickable links.
 * 
 * @author Alex Kuiper
 * 
 */
public class LinkHandler extends TagNodeHandler {


	protected String getHref(TagNode node) {
		return node.getAttributeByName("href");
	}

	protected URLSpan newSpan(String url) {
		return new URLSpan(url);
	}

	@Override
	public void handleTagNode(TagNode node, SpannableStringBuilder builder,
			int start, int end, SpanStack spanStack) {

		final String href = getHref(node);
		URL url = null;
		if(href != null && !href.startsWith("#")) {
			try {
				url = new URL(href);
			} catch (MalformedURLException ex) {
				if (getSpanner().getBaseDomain() != null) {
					try {
						url = new URL(getSpanner().getBaseDomain() + "/" + href);
					} catch (MalformedURLException ignore) {
					}
				}
			}
		}
		if(url != null) {
			spanStack.pushSpan(newSpan(url.toString()), start, end);
		} else {
			spanStack.pushSpan(newSpan(href), start, end);
		}
	}
}