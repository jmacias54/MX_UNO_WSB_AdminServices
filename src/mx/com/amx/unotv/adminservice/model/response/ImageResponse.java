package mx.com.amx.unotv.adminservice.model.response;

public class ImageResponse {

	private String pathImage;
	private String nameImage;

	public ImageResponse(String pathImage, String nameImage) {
		super();
		this.pathImage = pathImage;
		this.nameImage = nameImage;
	}

	public ImageResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}

	public String getNameImage() {
		return nameImage;
	}

	public void setNameImage(String nameImage) {
		this.nameImage = nameImage;
	}

	@Override
	public String toString() {
		return "ImageResponse [pathImage=" + pathImage + ", nameImage=" + nameImage + "]";
	}

}
