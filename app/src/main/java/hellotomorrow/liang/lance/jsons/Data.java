/**
  * Copyright 2019 bejson.com 
  */
package hellotomorrow.liang.lance.jsons;
import java.util.List;

/**
 * Auto-generated: 2019-07-26 20:47:9
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Data {

    private CardlistInfo cardlistInfo;
    private List<Cards> cards;
    private String scheme;
    public void setCardlistInfo(CardlistInfo cardlistInfo) {
         this.cardlistInfo = cardlistInfo;
     }
     public CardlistInfo getCardlistInfo() {
         return cardlistInfo;
     }

    public void setCards(List<Cards> cards) {
         this.cards = cards;
     }
     public List<Cards> getCards() {
         return cards;
     }

    public void setScheme(String scheme) {
         this.scheme = scheme;
     }
     public String getScheme() {
         return scheme;
     }

}