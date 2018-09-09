package co.udea.heroes.api.exception;

public class GeneralException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;
	private String translationKey;

	public GeneralException(String message) {
		this.message =  message;
	}

	public GeneralException(String message, String translationKey) {
		this.message =  message;
		this.translationKey = translationKey;
	}

	public String getTranslationKey() {
		return translationKey;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
