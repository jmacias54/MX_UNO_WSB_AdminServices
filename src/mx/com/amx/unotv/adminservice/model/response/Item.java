/**
 * 
 */
package mx.com.amx.unotv.adminservice.model.response;



import mx.com.amx.unotv.adminservice.model.main_video;
import mx.com.amx.unotv.adminservice.model.aditional_information;
import mx.com.amx.unotv.adminservice.model.main_image;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class Item {

	private String id;
	private String title;
	private String date;
	private String id_user;
	private String section;
	private String category;
	private String image;
	private String path_nota;
	private String type_nota;
	private String description;
	private String tags;

	private aditional_information aditional_information;
	private main_image main_image;
	private main_video main_video;

	private String contenido_nota;

	/**
	 * 
	 */
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getId_user() {
		return id_user;
	}

	public void setId_user(String id_user) {
		this.id_user = id_user;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPath_nota() {
		return path_nota;
	}

	public void setPath_nota(String path_nota) {
		this.path_nota = path_nota;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public aditional_information getAditional_information() {
		return aditional_information;
	}

	public void setAditional_information(aditional_information aditional_information) {
		this.aditional_information = aditional_information;
	}

	public main_image getMain_image() {
		return main_image;
	}

	public void setMain_image(main_image main_image) {
		this.main_image = main_image;
	}

	public main_video getMain_video() {
		return main_video;
	}

	public void setMain_video(main_video main_video) {
		this.main_video = main_video;
	}

	public String getContenido_nota() {
		return contenido_nota;
	}

	public void setContenido_nota(String contenido_nota) {
		this.contenido_nota = contenido_nota;
	}

	public String getType_nota() {
		return type_nota;
	}

	public void setType_nota(String type_nota) {
		this.type_nota = type_nota;
	}

	

	

}
