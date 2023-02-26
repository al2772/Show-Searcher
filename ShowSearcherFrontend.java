import java.util.List;
import java.util.Scanner;
public class ShowSearcherFrontend implements IShowSearcherFrontend{
  private ShowSearcherBackend Front;
  private boolean netflix;
  private boolean hulu;
  private boolean disney;
  private boolean prime;
  public ShowSearcherFrontend(ShowSearcherBackend backend){
    this.Front = backend;
  }
  Scanner in = new Scanner(System.in);
/**
   * This method drives the entire read, eval, print loop (repl) for the
   * Song Search App.  This loop will continue to run until the user 
   * explicitly enters the quit command. 
   */
  @Override
  public void runCommandLoop() {
    displayCommandMenu();
    String phrase = in.nextLine();
    while (!phrase.toLowerCase().equals("q") || !phrase.equals("4")) {
      if (phrase.toLowerCase().equals("t") || phrase.equals("1")) {
        titleSearch();
      }
      if (phrase.toLowerCase().equals("y") || phrase.equals("2")) {
        yearSearch();
      }
      if (phrase.toLowerCase().equals("f")|| phrase.equals("3")) {
        getFilters();
      }
      System.out.println("Would you like to do another search? enter to continue, q to quit ");
      phrase = in.nextLine();
      if (phrase.toLowerCase().equals("q") || phrase.equals("4")){
        break;
      }
      displayCommandMenu();
      phrase = in.nextLine();
    }
    if (phrase.toLowerCase().equals("q") || phrase.equals("4")) {
      System.out.println("Program Ended");
    }
  }
  @Override
  public void displayCommandMenu() { // prints command options to System.out
    //ShowSearcherFrontend yup = new ShowSearcherFrontend();
    String menu = "Command Menu:\n" +
        "    1) Search by [T]itle Word\n" +
        "    2) Search by [Y]ear First Produced\n" +
        "    3) [F]ilter by Streaming Provider\n" +
        "    4) [Q]uit\n" +
        "Choose a command from the menu above: \n";
    System.out.print(menu);
  }
  @Override
  public void displayShows(List<IShow> shows) { // displays a list of shows
    for (int i = 0; i< shows.size(); i++) {
      System.out.println(shows.get(i).getTitle());
    
    }
  }
  @Override
  public void titleSearch() { // reads word from System.in, displays results
    System.out.println("Choose a word that you would like to search for: ");
    String parameter = in.nextLine();
    if (parameter.matches("[a-zA-Z]+")) {
      System.out.println("Here are the results: \n");
      displayShows(Front.searchByTitleWord(parameter));
    }
    else {
      System.out.println("Not a valid word, Please try again: ");
    }
  }
  @Override
  public void yearSearch() { // reads year from System.in, displays results
    System.out.println("Choose a year that you would like to search for: ");
    String parameter = in.nextLine();
    //int parameter = in.nextInt();
    try {
      double number = Double.parseDouble(parameter);
      System.out.println("Here are the results: \n");
      displayShows(Front.searchByYear((int) number));
    } catch(NumberFormatException e){
      System.out.println("Not a valid year, Please try again: ");
    }
  }
  /**
   * This method gets the filters
   */
  public void getFilters() {
    System.out.println("Providers that shows are being searched for:");
    netflix = Front.getProviderFilter("N");
    hulu = Front.getProviderFilter("H");
    disney= Front.getProviderFilter("D");
    prime= Front.getProviderFilter("P");
    if (netflix) {
      System.out.print ("    1) _x_ [N]eflix\n");
    }
    else {
      System.out.print ("    1) ___ [N]eflix\n");
    }
    if (hulu) {
      System.out.print ("    2) _x_ [H]ulu\n");
    }
    else {
      System.out.print ("    2) ___ [H]ulu\n");
    }
    if (prime) {
      System.out.print ("    3) _x_ [P]rime Video\n");
    }
    else {
      System.out.print ("    3) ___ [P]rime Video\n");
    }
    if (disney) {
      System.out.print ("    4) _x_ [D]isney+\n");
    }
    else {
      System.out.print ("    4) ___ [D]isney+\n");
    }
    System.out.println("    5) [Q]uit toggling provider filters:");
    System.out.println("Choose a command from the menu above:");
    String parameter = in.nextLine();
    toggleFilters(parameter);
  }
  public void toggleFilters(String parameter) {
    if (parameter.toLowerCase().equals("q") || parameter.equals("5")) {
      return;
    }
    // now setting or toggling
    if (parameter.toLowerCase().equals("n") || parameter.equals("1")) {
      Front.toggleProviderFilter("n");
    }
    if (parameter.toLowerCase().equals("h") || parameter.equals("2")) {
      Front.toggleProviderFilter("h");
    }
    if (parameter.toLowerCase().equals("d") || parameter.equals("3")) {
      Front.toggleProviderFilter("d");
    }
    if (parameter.toLowerCase().equals("p") || parameter.equals("4")) {
      Front.toggleProviderFilter("p");
    }
    getFilters();
  }
}
