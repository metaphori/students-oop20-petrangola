package petrangola.controllers;

import petrangola.models.ObservableModel;

public interface ViewableController extends Controller {
  void setModel(ObservableModel model);
}
