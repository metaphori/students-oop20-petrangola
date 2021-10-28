package main.java.petrangola.controllers;

import main.java.petrangola.models.ObservableModel;

public interface ViewController extends Controller {
  void setModel(ObservableModel model);
}
