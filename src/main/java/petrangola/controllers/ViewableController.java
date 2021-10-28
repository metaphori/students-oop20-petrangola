package main.java.petrangola.controllers;

import main.java.petrangola.models.ObservableModel;

public interface ViewableController extends Controller {
  void setModel(ObservableModel model);
}
