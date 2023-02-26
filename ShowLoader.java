// --== CS400 Project Two File Header ==--
// Name: Rashed Almansoori
// CSL Username: rashed
// Email: ralmansoori@wisc.edu
// Lecture #: 002
// Notes to Grader: N/A
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author rashed almansoori
 * 
 *         class that loads shows from a comma delimited csv file
 */
public class ShowLoader implements IShowLoader {

  /**
   * method that loads the shows give the filepath
   * 
   * @param filepath
   * @return an arrayList of all the shows in the file
   */
  @Override
  public List<IShow> loadShows(String filepath) throws FileNotFoundException {
    File file = new File(filepath);
    Scanner scnr = new Scanner(file, "utf-8");
    scnr.nextLine();
    List<IShow> shows = new ArrayList<IShow>();
    String row;
    String title;
    int year;
    int rating;
    boolean netflix;
    boolean hulu;
    boolean primeVideo;
    boolean disney;
    String providers = "";

    while (scnr.hasNextLine()) { // while there are more shows in the file
      title = "";
      providers = "";
      year = -1;
      rating = -1;
      row = scnr.nextLine();
      row = row.substring(row.indexOf(',') + 1); // skip first variable
      row = row.substring(row.indexOf(',') + 1); // skip ID variable
      if (row.contains("\"")) { // if title has quotes in it
        title = row.substring(row.indexOf('\"'), row.lastIndexOf('\"') + 1);
        title = title.substring(1, title.length() - 1);
        row = row.substring(row.lastIndexOf('\"') + 2);
      } else { // if title doesnt have quotes in it
        title = row.substring(0, row.indexOf(','));
        row = row.substring(row.indexOf(',') + 1);
      }
      year = Integer.parseInt(row.substring(0, row.indexOf(','))); // take year as Integer
      row = row.substring(row.indexOf(',') + 1); 
      row = row.substring(row.indexOf(',') + 1); // skip age
      row = row.substring(row.indexOf(',') + 1); // skip IMDB
      rating = Integer.parseInt(row.substring(0, row.indexOf('/'))); // take the rating
      row = row.substring(row.indexOf(',') + 1);
      if (row.substring(0, row.indexOf(',')).equals("1")) {
        netflix = true;
      } else {
        netflix = false;
      }
      row = row.substring(row.indexOf(',') + 1);
      if (row.substring(0, row.indexOf(',')).equals("1")) {
        hulu = true;
      } else {
        hulu = false;
      }
      row = row.substring(row.indexOf(',') + 1);
      if (row.substring(0, row.indexOf(',')).equals("1")) {
        primeVideo = true;
      } else {
        primeVideo = false;
      }
      row = row.substring(row.indexOf(',') + 1);
      if (row.substring(0, row.indexOf(',')).equals("1")) {
        disney = true;
      } else {
        disney = false;
      }
      if (netflix) {
        providers = providers + "Netflix";
      }
      if (hulu) {
        providers = providers + "Hulu";
      }
      if (primeVideo) {
        providers = providers + "Prime Video";
      }
      if (disney) {
        providers = providers + "Disney+";
      }
      shows.add(new Show(title, year, rating, providers));

    }
    scnr.close();

    return shows;
  }

}