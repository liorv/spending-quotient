package com.lior.sq.shared;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.ImageData;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.ui.Image;

public class DynamicImageResource implements ImageResource
{
  static public ImageData scaleAndCropImage(Image image, double scaleToRatio,
      double sx, double sy, double sw, double sh)
  {
    Canvas canvasTmp = Canvas.createIfSupported();
    Context2d context = canvasTmp.getContext2d();

    double ch = (image.getHeight() * scaleToRatio) + 100;
    double cw = (image.getWidth() * scaleToRatio) + 100;

    canvasTmp.setCoordinateSpaceHeight((int) ch);
    canvasTmp.setCoordinateSpaceWidth((int) cw);

    ImageElement imageElement = ImageElement.as(image.getElement());

    // tell it to scale image
    context.scale(scaleToRatio, scaleToRatio);

    // draw image to canvas
    // s = source
    // d = destination
    double dx = 0;
    double dy = 0;
    context.drawImage(imageElement, dx, dy);

    // get image data - if you go greater than the scaled image nothing will
    // show up
    ImageData imageData = context.getImageData(sx, sy, sw, sh);

    return imageData;
  }

  public DynamicImageResource(String u) {
    height = 20;
    width = 20;
    url = u;
  }

  public DynamicImageResource(int h, int w, String u) {
    height = h;
    width = w;
    url = u;
  }

  private int height = 0;
  private int width = 0;
  private final String url;

  @Override
  public String getName() {
    return url;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getLeft() {
    return 0;
  }

  @Override
  public SafeUri getSafeUri() {
    return UriUtils.fromSafeConstant(getURL());
  }

  @Override
  public int getTop() {
    return 0;
  }

  @Override
  public String getURL() {
    return url;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public boolean isAnimated() {
    return false;
  }

}
