// --== CS400 Project One File Header ==--
// Name: Adam Lewandowski
// CSL Username: alewandowski
// Email: aclewandowsk@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: <any optional extra notes to your grader>

/**
 * Placeholder class for the Show class
 * 
 * @author Adam Lewandowski
 *
 */
public class Show implements IShow {

  private String title;
  private int year;
  private int rating;
  private String providers;

  // constructor
  public Show(String title, int year, int rating, String providers) {
    this.title = title;
    this.year = year;
    this.rating = rating;
    this.providers = providers;
  }

  @Override
  public int compareTo(IShow o) {

    return 0;
  }

  @Override
  public String getTitle() {

    return this.title;
  }

  @Override
  public int getYear() {

    return this.year;
  }

  @Override
  public int getRating() {

    return this.rating;
  }

  @Override
  public boolean isAvailableOn(String provider) {
    if (providers.contains(provider)) {
      return true;
    } else {
      return false;
    }
  }



}
