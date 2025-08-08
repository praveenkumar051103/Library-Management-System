import java.util.*;
public class Book{
    int id;
    String title;
    String author;
    int quantity;
    private List<Integer> ratings = new ArrayList<>();
    public Book(int id,String title,String author,int quantity){
        this.id=id;
        this.title=title;
        this.author=author;
        this.quantity=quantity;
    }
    public boolean isAvailable(){
        return quantity>0;
    }
    public void borrow(){
        if(quantity>0)quantity--;
    }
    public void returned(){
        quantity++;
    }

    public void addRating(int rating){
        if(rating>=1 && rating <= 5){
            ratings.add(rating);
        }else{
            System.out.println("Rating must be between 1 and 5.");
        }
    }

    public double getAverageRating(){
        if (ratings.isEmpty()) return 0.0;

        int sum=0;
        for(int r:ratings) sum+=r;
        return (double) sum/ratings.size();
    }
    public String getTitle(){
        return title;
    }
    public String toString(){
        return "ID: " + id + " | Title: " + title + " | Author: " + author + " | Available: " + quantity;
    }
}
