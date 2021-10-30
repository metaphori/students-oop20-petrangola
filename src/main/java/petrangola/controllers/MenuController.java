package petrangola.controllers;


import petrangola.views.ViewFactory;

public interface MenuController extends ViewableController {
  void setViewFactory(ViewFactory viewFactory);
}
