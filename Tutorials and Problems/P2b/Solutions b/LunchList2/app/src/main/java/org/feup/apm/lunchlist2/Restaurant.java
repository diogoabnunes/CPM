package org.feup.apm.lunchlist2;

import androidx.annotation.NonNull;

public class Restaurant {
  private String name="";
  private String address="";
  private String type="";
  private String notes="";

  public String getName() {
    return(name);
  }
  public void setName(String name) {
    this.name=name;
  }
  public String getAddress() {
    return(address);
  }
  public void setAddress(String address) {
    this.address=address;
  }
  public String getType() {
    return(type);
  }
  public void setType(String type) {
    this.type=type;
  }
  public String getNotes() {
    return(notes);
  }
  public void setNotes(String notes) {
    this.notes=notes;
  }
  public @NonNull
  String toString() {
    return(getName());
  }
}
