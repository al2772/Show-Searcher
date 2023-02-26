import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A class that utilizes a databaes in the form of a HashTableSortedSets object to add in movies
 * with their title and year of release, then implements methods to search based on those
 * parameters, filtered by the providers they're found on
 * 
 * @author Adam Lewandowski
 *
 */
public class ShowSearcherBackend implements IShowSearcherBackend {

  private int totalMovies;
  private HashTableSortedSets<String, IShow> titlesDatabase;
  private HashTableSortedSets<Integer, IShow> yearsDatabase;
  private ShowLoader showsList;
  private boolean netflixProvider = true;
  private boolean huluProvider = true;
  private boolean primeVideoProvider = true;
  private boolean disneyPlusProvider = true;


  public ShowSearcherBackend() {
    this.titlesDatabase = new HashTableSortedSets<String, IShow>();
    this.yearsDatabase = new HashTableSortedSets<Integer, IShow>();
    
  }

  
  @Override
  /**
   * Utilizes the add function of the HashTableSortedSets object to add a show by each word and it's
   * year associated with the valueType given by the show
   * 
   * @param show - The show to be added to the database
   */
  public void addShow(IShow show)  {
     
    // split the title into separate strings
    String[] tokens = show.getTitle().split(" ");

    // add the strings to database using IShow object
    for (int i = 0; i < tokens.length; i++) {
      titlesDatabase.add(tokens[i], show);
    }

    // add the year of the show to the database
    yearsDatabase.add(show.getYear(), show);

    // increment the total movies number
    totalMovies++;
    }

  

  @Override
  /**
   * Returns the number of shows in the data base that has been kept track of in the totalMovies
   * field
   * 
   */
  public int getNumberOfShows() {
    // call the size method from HashTableSortedSets
    return this.totalMovies;
  }

  @Override
  /**
   * Uses the provider and determines if the filter is going to be set for said provider based on if
   * filter is true. If it is false, it will set that provider to be filtered out
   * 
   * @param provider - the streaming provider to be used
   * @param filter   - determines if the provider's field should be true or false
   */
  public void setProviderFilter(String provider, boolean filter) {
    // check for Netflix and set field to true if filter is true
    if (provider.equalsIgnoreCase("N") && filter == true) {
      netflixProvider = true;
    } else if (provider.equals("N") && filter == false) {
      netflixProvider = false;
    }

    // check for Hulu and set field to true if the filter is true
    if (provider.equals("H") && filter == true) {
      huluProvider = true;
    } else if (provider.equals("H") && filter == false) {
      huluProvider = false;
    }

    // check for Disney+ and set field to true if the filter is true
    if (provider.equals("D") && filter == true) {
      disneyPlusProvider = true;
    } else if (provider.equals("D") && filter == false) {
      disneyPlusProvider = false;
    }

    // check for Prime Video and set field to true if the filter is true
    if (provider.equals("P") && filter == true) {
      primeVideoProvider = true;
    } else if (provider.equals("P") && filter == false) {
      primeVideoProvider = false;
    }

  }

  @Override
  /**
   * Looks at the parameter and returns the correct boolean value stored at the field associated
   * with it (true or false) as the filter
   * 
   * @param provider - the provider being looked for
   * @return - true if this provider's field has been set, or toggled, to true, otherwise false
   */
  public boolean getProviderFilter(String provider) {
    // check for Netflix
    if (provider.equals("N")) {
      return netflixProvider;
    }

    // check for Hulu
    if (provider.equals("H")) {
      return huluProvider;
    }

    // check for disney+
    if (provider.equals("D")) {
      return disneyPlusProvider;
    }

    // check for Prime Video
    if (provider.equals("P")) {
      return primeVideoProvider;
    }

    return false;
  }

  @Override
  /**
   * Toggles the filter for the designated provider to be false if true, or true if initially false
   * 
   * @param provider - the provider who's field is to be toggled
   */
  public void toggleProviderFilter(String provider) {
    // set each provider to false if true, and true if false
    
    if (provider.equals("n")) {
      if (netflixProvider == true) {
        netflixProvider = false;
      }
      else {
        netflixProvider = true;
      }
    } 
    
    // check hulu
    else if (provider.equals("h")) {
      if (huluProvider == true) {
        huluProvider = false;
      }
      else {
        huluProvider = true;
      }
    }

    // check disney+
    else if (provider.equals("d")) {
      if (disneyPlusProvider == true) {
        disneyPlusProvider = false;
      }
      else {
        disneyPlusProvider = true;
      }
    }

    // check Prime Video
    else if (provider.equals("p")) {
      if (primeVideoProvider == true) {
        primeVideoProvider = false;
      }
      else {
        primeVideoProvider = true;
      }
    }
  }

  @Override
  /**
   * Looks through the database to find shows with the designated word and adds them to a list
   * 
   * @param - word in title of movie to be looked for
   * @return - a properly sorted list of movies with this word
   */
  public List<IShow> searchByTitleWord(String word) {
    List<IShow> result = new ArrayList<IShow>();
    List<IShow> allShows = new ArrayList<IShow>();

    // set list equal to the shows with word KeyValue
    allShows = titlesDatabase.get(word);
    
    // loop through all the shows in the list
    for (int i = 0; i < allShows.size(); i++) {
     
      // System.out.println(nextShow.getTitle());
      // check if show is on netflix and if netflix filter is applied
      if (allShows.get(i).isAvailableOn("N") && netflixProvider == true) {
        result.add(allShows.get(i));
      }

      // check if show is on hulu and if hulu filter is applied
      if (allShows.get(i).isAvailableOn("H") && huluProvider == true) {
        result.add(allShows.get(i));
      }

      // check if show is on disney+ and if disney+ filter is applied
      if (allShows.get(i).isAvailableOn("D") && disneyPlusProvider == true) {
        result.add(allShows.get(i));
      }

      // check if show is on prime video and if prime video filter is applied
      if (allShows.get(i).isAvailableOn("P") && primeVideoProvider == true) {
        result.add(allShows.get(i));
      }
    }

    return result;
  }

  @Override
  /**
   * Looks through the database to find shows with the designated year and adds them to a list
   * 
   * @param year - year of movie to be searched for
   * @return - a properly sorted list of movies with this year
   */
  public List<IShow> searchByYear(int year) {
    List<IShow> result = new ArrayList<IShow>();
    List<IShow> allShows = new ArrayList<IShow>();

      // make sure the spot is not null
      allShows = yearsDatabase.get(year);
      
      // loop to find each show in the list
      for (int i = 0; i < allShows.size(); i++) {

      // check if show is on netflix and if netflix filter is applied
      if (allShows.get(i).isAvailableOn("N") && netflixProvider == true) {
        result.add(allShows.get(i));
      }

      // check if show is on hulu and if hulu filter is applied
      if (allShows.get(i).isAvailableOn("H") && huluProvider == true) {
        result.add(allShows.get(i));
      }

      // check if show is on disney+ and if disney+ filter is applied
      if (allShows.get(i).isAvailableOn("D") && disneyPlusProvider == true) {
        result.add(allShows.get(i));
      }

      // check if show is on prime video and if prime video filter is applied
      if (allShows.get(i).isAvailableOn("P") && primeVideoProvider == true) {
        result.add(allShows.get(i));
      }

    }


    return result;
  }

}
