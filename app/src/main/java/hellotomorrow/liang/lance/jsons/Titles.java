/**
  * Copyright 2019 bejson.com 
  */
package hellotomorrow.liang.lance.jsons;

import com.google.gson.annotations.SerializedName;

/**
 * Auto-generated: 2019-07-26 20:47:9
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Titles {

    @SerializedName("default")
    private boolean default_;
    private String title;
    public void setDefault(boolean default_) {
         this.default_ = default_;
     }
     public boolean getDefault() {
         return default_;
     }

    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

}