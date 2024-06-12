package com.suitsupply.constants;

public enum ApplicationPageTitles {

	HOME_PAGE_TITLE("Men's Suits | Wedding, Formal & Custom Suits"),
	SNEAKERS_PAGE_TITLE("Men's Sneakers - Leather, Suede, High Tops, Low Sneakers"),
	JACKETS_PAGE_TITLE("Men's Classic Jackets & Blazers - Blue Blazers & Grey Jackets");
	
	private final String title;

	ApplicationPageTitles(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
}
