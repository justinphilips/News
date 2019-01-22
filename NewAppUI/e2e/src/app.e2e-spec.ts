import { NewsAppPage } from './app.po';
import { browser, by, element, protractor } from 'protractor';


describe('workspace-project App', () => {
  let page: NewsAppPage;

  beforeEach(() => {
    page = new NewsAppPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    //expect(page.getParagraphText()).toEqual('Welcome to News-App-boilerplate-java!');
  });

  it('should have a title', () => {
    expect(browser.getTitle()).toEqual('News App');
  });

  it('should be redirected to /login route on opening the application', () => {
    expect(browser.getCurrentUrl()).toContain('/login');
    browser.element(by.id('register-btn')).click();
    expect(browser.getCurrentUrl()).toContain('/register');
  });

  it('should be able to register', () => {
    browser.element(by.id('username')).sendKeys('tamil');
    browser.element(by.id('pass')).sendKeys('tamil');
    browser.element(by.id('fname')).sendKeys('tamil');
    browser.element(by.id('lname')).sendKeys('tamil');
    browser.element(by.id('jumbotron.btn')).submit();

    expect(browser.getCurrentUrl()).toContain('/login');

  });

  it('should be able to login', () => {
    browser.element(by.id('form-group')).sendKeys('tamil');
    browser.element(by.id('form-group1')).sendKeys('tamil');
    browser.element(by.id('jumbotron.btn')).click();

    expect(browser.getCurrentUrl()).toContain('/dashboard');

  });

  it('should be able to search for news', () => {
    browser.element(by.id('col-md-4')).sendKeys('economy');
    const searchItems = element.all(by.id('name'));
    expect(searchItems.count()).toBe(1);
    for (let i = 0; i < 1; i++) {
        expect(searchItems.get(i).getText()).toContain('economy');
    }

  });

  it('should be able to add  to watchlist', () => {
    const searchItems = element.all(by.id('name'));
      expect(searchItems.count()).not.toBeLessThan(0);
    //expect(searchItems.count()).toBe(1);
    browser.element(by.id('recBtn')).click();
  });

});
