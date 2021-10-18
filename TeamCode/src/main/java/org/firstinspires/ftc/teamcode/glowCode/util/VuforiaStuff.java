package org.firstinspires.ftc.teamcode.glowCode.util;
////copied from 10435 github, 11/27/19

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;

import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.graphics.Bitmap.createBitmap;
import static android.graphics.Bitmap.createScaledBitmap;

public class VuforiaStuff {

    VuforiaLocalizer vuforia;

    public VuforiaStuff(VuforiaLocalizer vuforia) {
        this.vuforia = vuforia;
    }

    public enum skystonePos {
        ZERO, ONE, FOUR, ERR
    }

    public skystonePos vuforiascan(boolean saveBitmaps, boolean red) {
        Image rgbImage = null;
        int rgbTries = 0;
        /*
        double colorcountL = 0;
        double colorcountC = 0;
        double colorcountR = 0;
        */
        double yellowCountL = 1;
        double yellowCountC = 1;
        double yellowCountR = 1;

        double blackCountL = 1;
        double blackCountC = 1;
        double blackCountR = 1;
        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);
        VuforiaLocalizer.CloseableFrame closeableFrame = null;
        this.vuforia.setFrameQueueCapacity(1);
        while (rgbImage == null) {
            try {
                closeableFrame = this.vuforia.getFrameQueue().take();
                long numImages = closeableFrame.getNumImages();

                for (int i = 0; i < numImages; i++) {
                    if (closeableFrame.getImage(i).getFormat() == PIXEL_FORMAT.RGB565) {
                        rgbImage = closeableFrame.getImage(i);
                        if (rgbImage != null) {
                            break;
                        }
                    }
                }
            } catch (InterruptedException exc) {

            } finally {
                if (closeableFrame != null) closeableFrame.close();
            }
        }

        if (rgbImage != null) {

            // copy the bitmap from the Vuforia frame
            Bitmap bitmap = Bitmap.createBitmap(rgbImage.getWidth(), rgbImage.getHeight(), Bitmap.Config.RGB_565);
            bitmap.copyPixelsFromBuffer(rgbImage.getPixels());

            String path = Environment.getExternalStorageDirectory().toString();
            FileOutputStream out = null;

            String bitmapName;
            String croppedBitmapName;

            if (red) {
                bitmapName = "BitmapRED.png";
                croppedBitmapName = "BitmapCroppedRED.png";
            } else {
                bitmapName = "BitmapBLUE.png";
                croppedBitmapName = "BitmapCroppedBLUE.png";
            }

            //Save bitmap to file
            if (saveBitmaps) {
                try {
                    File file = new File(path, bitmapName);
                    out = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (out != null) {
                            out.flush();
                            out.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            int cropStartX;
            int cropStartY;
            int cropWidth;
            int cropHeight;


            if (red) {
                cropStartX = (int) ((230.0 / 720.0) * bitmap.getWidth());
                cropStartY = (int) ((180.0 / 480.0) * bitmap.getHeight());
                // 450 and 720
                cropWidth = (int) ((460.0 / 720.0) * bitmap.getWidth());
                cropHeight = (int) ((120.0 / 480.0) * bitmap.getHeight());
            } else {
                //orgin is top left
                cropStartX = (int) ((400 / 1280.0) * bitmap.getWidth()); //was 320
                cropStartY = (int) ((150 / 720.0) * bitmap.getHeight()); //was 360
                cropWidth = (int) ((150 / 1280.0) * bitmap.getWidth()); //was 670
                cropHeight = (int) ((125 / 720.0) * bitmap.getHeight());
            }

//            DbgLog.msg("10435 vuforiascan"
//                    + " cropStartX: " + cropStartX
//                    + " cropStartY: " + cropStartY
//                    + " cropWidth: " + cropWidth
//                    + " cropHeight: " + cropHeight
//                    + " Width: " + bitmap.getWidth()
//                    + " Height: " + bitmap.getHeight()
//            );


            bitmap = createBitmap(bitmap, cropStartX, cropStartY, cropWidth, cropHeight); //Cropped Bitmap to show only stones

            // Save cropped bitmap to file
            if (saveBitmaps) {
                try {
                    File file = new File(path, croppedBitmapName);
                    out = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (out != null) {
                            out.flush();
                            out.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            bitmap = createScaledBitmap(bitmap, 110, 20, true); //Compress bitmap to reduce scan time


            int height;
            int width;
            int pixel;
            int bitmapWidth = bitmap.getWidth();
            int bitmapHeight = bitmap.getHeight();
            int colHeight = (int) ((double) bitmapHeight / 6.0);
            int colorLStartCol = (int) ((double) bitmapHeight * (1.0 / 6.0) - ((double) colHeight / 2.0));
            int colorCStartCol = (int) ((double) bitmapHeight * (3.0 / 6.0) - ((double) colHeight / 2.0));
            int colorRStartCol = (int) ((double) bitmapHeight * (5.0 / 6.0) - ((double) colHeight / 2.0));

            for (width = 0; width < bitmapWidth; ++width) {
                for (height = colorLStartCol; height < colorLStartCol + colHeight; ++height) {
                    pixel = bitmap.getPixel(width, height);
                    /*
                    if (Color.red(pixel) < 200 || Color.green(pixel) < 200 || Color.blue(pixel) < 200) {
                        yellowCountL += Color.red(pixel);
                        blackCountL += Color.blue(pixel);
                    }
                    */
                    if (Color.red(pixel) > 225 && Color.green(pixel) > 140 && Color.blue(pixel) < 20) {
                        yellowCountR += 1;
                        //orange
                    } else if (Color.red(pixel) < 200 && Color.green(pixel) < 200 && Color.blue(pixel) < 200) {
                        blackCountR += 1;
                    }

                    //colorcountL += Color.red(pixel) + Color.green(pixel) + Color.blue(pixel);
                }
                for (height = colorCStartCol; height < colorCStartCol + colHeight; ++height) {
                    pixel = bitmap.getPixel(width, height);

                    /*
                    if (Color.red(pixel) < 200 || Color.green(pixel) < 200 || Color.blue(pixel) < 200) {
                        yellowCountC += Color.red(pixel);
                        blackCountC += Color.blue(pixel);
                    }
                     */
                    if (Color.red(pixel) > 225 && Color.green(pixel) > 140 && Color.blue(pixel) < 20) {
                        yellowCountR += 1;
                        //orange
                    } else if (Color.red(pixel) < 200 && Color.green(pixel) < 200 && Color.blue(pixel) < 200) {
                        blackCountR += 1;
                    }


                    //colorcountC += Color.red(pixel) + Color.green(pixel) + Color.blue(pixel);
                }

                for (height = colorRStartCol; height < colorRStartCol + colHeight; ++height) {
                    pixel = bitmap.getPixel(width, height);
                    /*
                    if (Color.red(pixel) < 200 || Color.green(pixel) < 200 || Color.blue(pixel) < 200) {
                        yellowCountR += Color.red(pixel);
                        blackCountR += Color.blue(pixel);
                    }
                    */
                    if (Color.red(pixel) > 225 && Color.green(pixel) > 140 && Color.blue(pixel) < 20) {
                        yellowCountR += 1;
                        //orange
                    } else if (Color.red(pixel) < 200 && Color.green(pixel) < 200 && Color.blue(pixel) < 200) {
                        blackCountR += 1;
                    }

                    //colorcountR += Color.red(pixel) + Color.green(pixel) + Color.blue(pixel);
                }
            }
        }

        double blackYellowRatioL = blackCountL / yellowCountL;
        double blackYellowRatioC = blackCountC / yellowCountC;
        double blackYellowRatioR = blackCountR / yellowCountR;


        skystonePos pos;
        /*
        DbgLog.msg("color L: " + Double.toString(colorcountL));
        DbgLog.msg("color C: " + Double.toString(colorcountC));
        DbgLog.msg("color R: " + Double.toString(colorcountR));
        if (colorcountL < colorcountC && colorcountL < colorcountR) {
            pos = skystonePos.LEFT;
        } else if (colorcountC < colorcountL && colorcountC < colorcountR) {
            pos = skystonePos.CENTER;
        } else {
            pos = skystonePos.RIGHT;
        }
*/
        if (yellowCountL > blackCountL && yellowCountR > blackCountR) {
            pos = skystonePos.FOUR; //was left, but image upside down
            // else if (yellowCountL <=  && yellowCountR > 55000) {
            //pos = skystonePos.ONE;
        } else if (yellowCountL < blackCountL && yellowCountR < blackCountR) {
            pos = skystonePos.ZERO; //was right, but image upside down
        } else pos = skystonePos.ONE;

//        DbgLog.msg("black/yellow L: " + blackCountL + "/" + yellowCountL);
//        DbgLog.msg("black/yellow C: " + blackCountC + "/" + yellowCountC);
//        DbgLog.msg("black/yellow R: " + blackCountR + "/" + yellowCountR);

        return pos;
    }
}