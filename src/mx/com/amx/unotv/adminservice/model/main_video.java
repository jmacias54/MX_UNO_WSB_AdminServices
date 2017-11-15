
package mx.com.amx.unotv.adminservice.model;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class main_video {

	private String type;
	private String p_code;
	private String player_id;
	private String content_id;
	private String video_youtube_id;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getP_code() {
		return p_code;
	}

	public void setP_code(String p_code) {
		this.p_code = p_code;
	}

	public String getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(String player_id) {
		this.player_id = player_id;
	}

	public String getContent_id() {
		return content_id;
	}

	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}

	public String getVideo_youtube_id() {
		return video_youtube_id;
	}

	public void setVideo_youtube_id(String video_youtube_id) {
		this.video_youtube_id = video_youtube_id;
	}

	/**
	 * 
	 */
	public main_video() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param type
	 * @param p_code
	 * @param player_id
	 * @param content_id
	 * @param video_youtube_id
	 */
	public main_video(String type, String p_code, String player_id, String content_id, String video_youtube_id) {
		super();
		this.type = type;
		this.p_code = p_code;
		this.player_id = player_id;
		this.content_id = content_id;
		this.video_youtube_id = video_youtube_id;
	}

}
