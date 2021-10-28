package main.java.petrangola.controllers;


import main.java.petrangola.views.ViewFactory;

public interface MenuController extends ViewableController {
  void setViewFactory(ViewFactory viewFactory);
}
