package servlets;



import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;


//this is just a plain old java Object

/*@SuppressWarnings("deprecation")
//it is advised for pojo members to be private
@Entity
@Table(name="film")*/
public class FilmPOJO {
	/*
	filmRow.setFilmId(resultSet.getInt("film_id"));
	filmRow.setTitle(resultSet.getString("title"));
	filmRow.set Description(resultSet.getString("description"));
	filmRow.set Year(resultSet.getDate("film_id"));
	filmRow.set LanguageId(resultSet.getInt("Language_id"));
	filmRow.set OriginalLanguageId(resultSet.getInt("original_Language_id"));
	filmRow.set RentalDuration(resultSet.getInt("rental_duration"));
	filmRow.set RentalRate(resultSet.getFloat("rental_rate"));
	filmRow.set Length(resultSet.getInt("length"));
	filmRow.set ReplacementCost(resultSet.getFloat("rental_rate"));
	filmRow.set Rating(resultSet.getString("rating"));
	filmRow.set SpecialFeatures(resultSet.getString("special_features"));
	filmRow.set LastUpdate(resultSet.getTime("last_update"));
	*/
	
	private int FilmId;
	private String Title;
	private String Description;
	private String Year;
	private String director;
	private int LanguageId;
	private int OriginalLanguageId;
	private int RentalDuration;
	private float RentalRate; 
	private int Length;
	private float ReplacementCost;
	private String Rating;
	private String SpecialFeatures;
	private String LastUpdate;
	
	public int getFilmId() {
		return FilmId;
	}
	public void setFilmId(int filmId) {
		this.FilmId = filmId;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		this.Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		this.Description = description;
	}
	public String getYear() {
		return Year;
	}
	public void setYear(String year) {
		this.Year = year;
	}
	public int getLanguageId() {
		return LanguageId;
	}
	public void setLanguageId(int languageId) {
		this.LanguageId = languageId;
	}
	public int getOriginalLanguageId() {
		return OriginalLanguageId;
	}
	public void setOriginalLanguageId(int originalLanguageId) {
		this.OriginalLanguageId = originalLanguageId;
	}
	public int getRentalDuration() {
		return RentalDuration;
	}
	public void setRentalDuration(int rentalDuration) {
		this.RentalDuration = rentalDuration;
	}
	public float getRentalRate() {
		return RentalRate;
	}
	public void setRentalRate(float rentalRate) {
		this.RentalRate = rentalRate;
	}
	public int getLength() {
		return Length;
	}
	public void setLength(int length) {
		this.Length = length;
	}
	public float getReplacementCost() {
		return ReplacementCost;
	}
	public void setReplacementCost(float replacementCost) {
		this.ReplacementCost = replacementCost;
	}
	public String getRating() {
		return Rating;
	}
	public void setRating(String rating) {
		this.Rating = rating;
	}
	public String getSpecialFeatures() {
		return SpecialFeatures;
	}
	public void setSpecialFeatures(String specialFeatures) {
		this.SpecialFeatures = specialFeatures;
	}
	public String getLastUpdate() {
		return LastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.LastUpdate = lastUpdate;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}		
}
