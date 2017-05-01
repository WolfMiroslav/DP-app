package utils;

public class CParserConstants {
	// LOGS ATTRIBUTES CONSTANTS - gemeral
	public static final String MODIFIERS_ATTR = "modifiers=";
	public static final String TIMESTAMP_ATTR = "timestamp=";
	public static final String INACTIVITY_TIME_ATTR = "INACTIVITY_TIME=";
	
	// LOGS ATTRIBUTES CONSTANTS - mouse
	public static final String CLICK_COUNT_ATTR = "clickCount=";	
	
	// LOGS ATTRIBUTES CONSTANTS - keyboard
	public static final String KEY_TEXT_ATTR = "keyText=";
	public static final String KEY_LOCATION_ATTR = "keyLocation=";
	public static final String MY_CODE_ATTR = "myCode=";
	public static final String KEY_CHAR_ATTR = "keyChar=";
	public static final String KEY_ORDER_ATTR = "keyOrder=";
	
	
	// REGEXP CONSTANTS - general
	public static final String ACTIVE_WINDOW_TITLE_REGEXP = "Active window title:.*";		
	public static final String INACTIVITY_TIME_REGEXP = "INACTIVITY_TIME.*";
	public static final String SESSION_TIME_REGEXP = ".*SESSION_TIME=.*";
	
	public static final String EMOTION_REGEXP = ".*emotion=.*";
	public static final String TIMESTAMP_REGEXP = ".*timestamp=.*";
	public static final String NEUTRAL_EMOTION_REGEXP = ".*emotion=NEUTRAL.*";
	public static final String POSITIVE_EMOTION_REGEXP = ".*emotion=POSITIVE.*";
	public static final String NEGATIVE_EMOTION_REGEXP = ".*emotion=NEGATIVE.*";
	
	// REGEXP CONSTANTS - mouse
	public static final String MOUSE_PRESSED_REGEXP = "NATIVE_MOUSE_PRESSED.*";
	public static final String MOUSE_RELEASED_REGEXP = "NATIVE_MOUSE_RELEASED.*";
	public static final String MOUSE_DRAGGED_REGEXP = "NATIVE_MOUSE_DRAGGED.*";
	public static final String MOUSE_MOVED_REGEXP = "NATIVE_MOUSE_MOVED.*";
	public static final String MOUSE_WHEEL_REGEXP = "NATIVE_MOUSE_WHEEL.*";
	
	public static final String MOUSE_SCROLL_UP_REGEXP = ".*wheelRotation=-1.*";
	public static final String MOUSE_SCROLL_DOWN_REGEXP = ".*wheelRotation=1.*";
	public static final String LEFT_CLICK_REGEXP = ".*button=1.*";
	public static final String RIGHT_CLICK_REGEXP = ".*button=2.*";
	public static final String MIDDLE_CLICK_REGEXP = ".*button=3.*";
		
	// REGEXP CONSTANTS - keyboard
	public static final String KEY_PRESSED_REGEXP = "NATIVE_KEY_PRESSED.*";
	public static final String KEY_RELEASED_REGEXP = "NATIVE_KEY_RELEASED.*";
	public static final String KEY_TYPED_REGEXP = "NATIVE_KEY_TYPED.*";
	
	public static final String KEY_CHAR_REGEXP = ".*keyChar=.*";
}
