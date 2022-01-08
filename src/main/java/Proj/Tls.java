package Proj;

import com.microsoft.playwright.*;

public class Tls extends Thread {
	
	        Playwright playwright ;
	        Browser browser ;
	        BrowserContext browsercontext;
	        BrowserContext secondcontext;
	        Page page;
	        Page second_page;
	        int index ;
	     
	        public Tls() {
	        	super();
				this.playwright = null;
				this.browser = null;
				this.browsercontext = null;
				this.secondcontext = null;
				this.page = null;
				this.second_page = null;
				this.index = 0;
	        	
	        }
	        
			

			
		
			
			public Tls(Playwright playwright, Browser browser, BrowserContext browsercontext,
					BrowserContext secondcontext, Page page, Page second_page, int index) {
				super();
				this.playwright = playwright;
				this.browser = browser;
				this.browsercontext = browsercontext;
				this.secondcontext = secondcontext;
				this.page = page;
				this.second_page = second_page;
				this.index = index;
			}






			public BrowserContext getSecondcontext() {
				return secondcontext;
			}

			public void setSecondcontext(BrowserContext secondcontext) {
				this.secondcontext = secondcontext;
			}

			public Playwright getPlaywright() {
				return playwright;
			}
			public void setPlaywright(Playwright playwright) {
				this.playwright = playwright;
			}
			public Browser getBrowser() {
				return browser;
			}
			public void setBrowser(Browser browser) {
				this.browser = browser;
			}
			public BrowserContext getBrowsercontext() {
				return browsercontext;
			}
			public void setBrowsercontext(BrowserContext browsercontext) {
				this.browsercontext = browsercontext;
			}
			public Page getPage() {
				return page;
			}
			public void setPage(Page page) {
				this.page = page;
			}
			public Page getSecond_page() {
				return second_page;
			}
			public void setSecond_page(Page second_page) {
				this.second_page = second_page;
			}
			public int getIndex() {
				return index;
			}
			public void setIndex(int index) {
				this.index = index;
			}
	        
	        
	        
	        
	        
	        
}
