package pl.anitakowalczyk.surwejlansapi.service.email;

import org.springframework.stereotype.Service;
import pl.anitakowalczyk.surwejlansapi.dao.entity.AllegroOffer;
import pl.anitakowalczyk.surwejlansapi.dao.entity.NewOffer;
import pl.anitakowalczyk.surwejlansapi.dao.response.AllegroOfferListResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HtmlEmailBuilderServiceImpl implements HtmlEmailBuilderService {

    private static String buildOfferHref(AllegroOffer allegroOffer) {
        return "<li><a href=\"http://allegro.pl/show_item.php?item=" + allegroOffer.getAllegroId() + "\">"
                + allegroOffer.getName() + "</a></li>";
    }

    public String buildOfferListEmail(AllegroOfferListResponse offerListResponse) {
        String offerList = offerListResponse.regular.stream()
                .map(HtmlEmailBuilderServiceImpl::buildOfferHref)
                .collect(Collectors.joining());
        String html = "<!DOCTYPE html><html><body><ul>" + offerList + "</ul></body></html>";
        return html;
    }

    private static String buildImageOfferHref(NewOffer offer) {
        return  "<tr width=\"100%\" style=\" background-color: white;\">" +
                "<td width=\"60%\">" +
                "<a style=\"text-decoration: none; color: black;\" href=\"http://allegro.pl/show_item.php?item=" + offer.getAllegroId() + "\">"  +
                offer.getName() + "</a></td>" +
                "<td width=\"40%\">" +
                "<a style=\"text-decoration: none;\" href=\"http://allegro.pl/show_item.php?item=" + offer.getAllegroId() + "\">"  +
                "<img src='" + offer.getImageUrl() + "' height='50%' width='50%'></a></td>" +
                "</tr></a>";
    }


    public String buildOfferImageListEmail(List<NewOffer> offers) {
        String offerList = offers.stream()
                .map(HtmlEmailBuilderServiceImpl::buildImageOfferHref)
                .collect(Collectors.joining());
        String imageHtml = "<!DOCTYPE html><html><style>" +
                "</style><body style=\"font-family:  Courier New, monospace; font-size: 13px; background-color: #FFDFBF;\">" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"900\">" +
                "<tr><td bgcolor=\"#ffffff\" style=\"padding: 40px 30px 40px 30px;\">allegro rest api</td></tr>" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"background-color: #FFDFBF;\">\n" +
                offerList + "</table></table></body></html>";
        return imageHtml;
    }
}