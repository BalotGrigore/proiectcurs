package proiect.grig.Backbone;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Elements {

    WebDriver driver;

    public Elements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize @FindBy elements
    }

    // Accept all cookies button
    @FindBy(css = "body > div.gdpr-cookie-banner.js-gdpr-cookie-banner.py-2.px-0.show > div > div.col-xs-12.col-sm-5.col-md-4.col-lg-3.cookie-banner-buttons > button.btn.btn-primary.btn-block.js-accept.gtm_h76e8zjgoo")
    WebElement cookieButton;

    // NavAndClick() elements
    @FindBy(css = "#auxiliary > div > div > ul:nth-child(1) > li") // Main menu button Produse
    WebElement Produse;
    @FindBy(xpath = "//*[text()='TV, Audio-Video & Foto']") // Submenu button TV, Audio-Video & Foto
    WebElement TVAudioVideoFoto;
    @FindBy(xpath = "//*[text()='Televizoare']") // Submenu button Televizoare
    WebElement Televizoare;

    // OfertaZilei_Navigate() elements
    @FindBy(css = "#auxiliary > div > div > ul:nth-child(3) > li:nth-child(1) > a") // Main menu button Oferta zilei
    WebElement OfertaZilei;

    // Resigilate_navigate() elements
    @FindBy(css = "#auxiliary > div > div > ul:nth-child(3) > li:nth-child(2) > a") // Main menu button Resigilate
    WebElement Resigilate;
    @FindBy(css = "#main-container > section.page-section.page-listing-v2 > div > div.clearfix.pb-7 > div.page-container > div:nth-child(1) > div.listing-panel-heading.hidden-xs > div > div.listing-page-title.js-head-title")
    WebElement ProduseResigilateBigText;
    @FindBy(css = "#main-container > section.page-section.page-listing-v2 > div > div.page-header > div.breadcrumb-outer-holder.breadcrumb-lighten.js-breadcrumb-holder > div > ol > li")
    WebElement ProduseResigilateSmallText;

    // Genius() elements
    @FindBy(css = "#auxiliary > div > div > ul:nth-child(3) > li:nth-child(3) > a") // Main menu button Genius
    WebElement Genius;

    // GeniusDeals() elements
    @FindBy(css = "#auxiliary > div > div > ul:nth-child(3) > li:nth-child(4) > a") // Main menu button Genius Deals
    WebElement GeniusDeals;

    // EasyBuyBack() elements
    @FindBy(css = "#auxiliary > div > div > ul:nth-child(3) > li:nth-child(5) > a") // Main menu button for EasyBuyBack
    WebElement EasyBuyBack;
    @FindBy(css = "#navbar-aux-dropdown") // dropdown menu available when page not maximised "Vezi mai mult "
    WebElement VeziMaiMult;
    @FindBy(xpath = "//*[text()='Easy BuyBack ']") // First version for selecting Easu BuyBack from the dropdown
    WebElement EasyBuyBackDropdown1;
    @FindBy(xpath = "(//*[text()='Easy BuyBack '])[2]") // Second version for selecting Easu BuyBack from the dropdown
    WebElement EasyBuyBackDropdown2;

    // OferteleEmag()
    @FindBy(css = "#auxiliary > div > div > ul:nth-child(3) > li:nth-child(6) > a") // Main menu button for OferteleEmag
    WebElement OferteleEmag;
    @FindBy(xpath = "//*[text()='Ofertele eMAG']") // OferteleEmag button from the dropdown
    WebElement OferteleEmagDropdown;
    @FindBy(xpath = "(//*[text()='Ofertele eMAG'])[2]") // OferteleEmag button from the dropdown
    WebElement OferteleEmagDropdown2;
    @FindBy(css = "body > div.ph-widget.ph-popup.ph-dropdown-popup.ph-visible.ph-popup-s.ph-popup-w.ph-popup-bottom > div > ul > li:nth-child(6) > a")
    WebElement JustUseCss;

    // FavProduct() elements
    @FindBy(css = "#my_wishlist > i") // Favorite button
    WebElement FavoriteButton;
    @FindBy(xpath = "//a[@href='/favorites?ref=ua_favorites']") // Button vezi toate produsele favorite
    WebElement SeeFavProducts;
    @FindBy(xpath = "//*[text()='Adauga produsele preferate la Favorite.']") // Empty FavBtn text when hovered over
    WebElement TextFavoriteIsEmpty;
    @FindBy(xpath = "//*[contains(@class, 'add-to-favorites btn')]") // First product on the page favorite button
    WebElement ProductFavButton;
    @FindBy(xpath = "(//*[contains(@class, 'add-to-favorites btn')])[2]") // First product on the page favorite button
    WebElement ProductFavButton2;
    @FindBy(css = "body > div.ns-wrap-top-right > div > div > div > div > div.table-cell.col-xs-9") // Favorite product
                                                                                                    // added
                                                                                                    // notification
    WebElement AddedToFavNotification;
    @FindBy(xpath = "//*[text()='1']") // We check we have 1 product as favorite
    WebElement FavBtnIncremented1;
    @FindBy(xpath = "//*[text()='2']") // We check we have 1 product as favorite
    WebElement FavBtnIncremented2;
    @FindBy(xpath = "//*[text()='Sterge']") // Delete button for first product from the favorite page
    WebElement DeleteProductFromFav;
    @FindBy(xpath = "//*[text()='Adauga in Cos']") // Product Add to cart button
    WebElement AddToCart;
    @FindBy(css = "body > div.ph-modal.modal.fade.ph-modal-fullscreen.product-purchased-modal.modal-version-Rec.in > div > div > div.modal-header > h4") // Element
                                                                                                                                                         // with
                                                                                                                                                         // "Produsul
                                                                                                                                                         // a
                                                                                                                                                         // fost
                                                                                                                                                         // adaugat
                                                                                                                                                         // in
                                                                                                                                                         // cos"
                                                                                                                                                         // text
    WebElement ProductAddedToCartText;
    @FindBy(xpath = "//*[text()='Vezi detalii cos']") // Vezi detalii cos button text
    WebElement VeziDetaliiCosText;
    @FindBy(xpath = "//*[@class='btn btn-primary btn-sm btn-block']") // Vezi detalii cos button
    WebElement VeziDetaliiCosButton;
    @FindBy(xpath = "//*[text()='Coșul meu']") // Element containing the text "Coșul meu"
    WebElement CosulMeuText;
    @FindBy(xpath = "//a[contains(@class, 'btn-primary')]") // Continua button
    WebElement ContinuaBtn;
    @FindBy(xpath = "//*[text()='Introdu adresa de email']") // element with text
    WebElement LoginPageText;

    // Dedeman.ro login elements
    @FindBy(css = "#notice-cookie-block > div > div.cookie-bar-inner-content > div.default-content > div.cookie-bar-section.cookie-bar-bottom > ul > li:nth-child(2) > button")
    public WebElement AcceptCookieDedeman;
    @FindBy(css = "#html-body > div.page-wrapper > header > div.header.content > div.flex.inner-header > div.hide-on-mobile.user-dropdowns > div > div:nth-child(2) > span > label")
    public WebElement ContulMeu;
    @FindBy(css = "#ui-id-2 > div > div > a.full-width.align-center.action.orange.primary.small.log-in > span")
    public WebElement Authenticate;
    @FindBy(id = "email-input")
    public WebElement CampEmail;
    @FindBy(id = "password-input")
    public WebElement CampParola;
    @FindBy(css = "#maincontent > div.columns > div > div.login-container > div.gigya-form-box > form > fieldset > button")
    public WebElement ContinuaBtnDedeman;
    @FindBy(css = "#maincontent > div.columns > div.column.main > div.page-title-wrapper > h1")
    public WebElement ContulMeuText;
    @FindBy(css = "#maincontent > div.columns > div > div.login-container > div.gigya-form-box > form > fieldset > div:nth-child(1) > label")
    public WebElement textRequestCredentials;
}
