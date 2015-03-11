package cartier.cartierTest;

import java.util.ArrayList;

public class LogUtil {
	public static ArrayList<String> STEPELEMENTS = new ArrayList<String>();
	private static String STEP = "\n-步骤: ";
	private static String OPERATE = "\n-      操作: ";
	private static String DATA = "\n-      数据: ";
	private static String[] MATCH_TYPES = { " 字符串 [", " id [",
			" 题目为 [", " 包含字符串 ["};
	private static String[] MATCH_RESULT = { "] 存在",
			"] 不存在", " to left.", " to right." };
	
	public static void clearLog() {
		STEPELEMENTS.clear();
	}

	public static ArrayList<String> getLog() {
		return STEPELEMENTS;
	}

	public static void addOperate(int actionType, int matchType,
			String matchContent, int matchResult) {
		String step = "";
		switch (actionType) {
		case (ActionType.SCROLL_AND_CLICK):
			step = OPERATE + "滚动到" + MATCH_TYPES[matchType]
					+ matchContent + "] 并点击。";
			break;
		case (ActionType.SCROLL_AND_CHECK):
			step = OPERATE + "滚动并检查"
					+ MATCH_TYPES[matchType] + matchContent
					+ MATCH_RESULT[matchResult];
			break;
		case (ActionType.CLICK):
			step = OPERATE + "点击" + MATCH_TYPES[matchType]
					+ matchContent + "]";
			break;
		case (ActionType.CHECK):
			step = OPERATE + "检查" + MATCH_TYPES[matchType]
					+ matchContent + MATCH_RESULT[matchResult];
			break;
		case (ActionType.OPEN_ACTION_BAR):
			step = OPERATE + "Open action bar overflow or options menu.";
			break;
		case (ActionType.FIND_AD):
			step = OPERATE + "找到" + MATCH_TYPES[matchType] + matchContent
					+ "]的广告";
			break;
		case (ActionType.PRESS_HARDWARE):
			step = OPERATE + "按" + matchContent + "";
			break;
		case (ActionType.TAP_UP):
			step = OPERATE + "点击返回按钮";
			break;
		case (ActionType.SELECT):
			step = OPERATE + "选择 [" + matchContent + MATCH_TYPES[matchType];
			break;
		default:
			step = "\n错误：addOperate方法调用错误！\n\n";
		}
		STEPELEMENTS.add(step);
	}

	public static void addOperate(int actionType, String... matchContents) {
		String step;
		if (actionType > ActionType.SELECT_CATEGORY
				|| actionType < ActionType.SET_ATTRIBUTE)
			step = "\n错误：addOperate方法调用错误！\n\n";
		else if (actionType == ActionType.SET_ATTRIBUTE)
			step = OPERATE + "筛选 [" + matchContents[0] + "] 为 ["
					+ matchContents[1] + "]";
		else {
			if (actionType == ActionType.SELECT_CATEGORY)
				step = OPERATE + "选择类别为 ["+matchContents[0]+"]";
			else
				step = OPERATE + "选择地区为 ["+matchContents[0]+"]";
		}
		STEPELEMENTS.add(step);
	}

    public static void addOperate(int actionType, int matchContents) {
        String step;
        if (actionType != ActionType.SELECT_LOCATION)
            step = "\n错误：addOperate方法调用错误！\n\n";
        else {
            step = OPERATE + "选择第 ["+matchContents+"] 个地区";
        }
        STEPELEMENTS.add(step);
    }

	public static void addStep(String content) {
		STEPELEMENTS.add(STEP + content);
	}

	public static void addData(String dataName, String dataContent) {
		STEPELEMENTS.add(DATA + dataName + " [ " + dataContent + " ]");
	}

	public static class ActionType {
		public static final int SCROLL_AND_CLICK = 0;
		public static final int SCROLL_AND_CHECK = 1;
		public static final int CLICK = 2;
		public static final int CHECK = 3;
		public static final int OPEN_ACTION_BAR = 4;
		public static final int FIND_AD = 5;
		public static final int PRESS_HARDWARE = 6;
		public static final int TAP_UP = 7;
		public static final int SELECT = 8;
		public static final int SET_ATTRIBUTE = 9;
		public static final int SELECT_LOCATION = 10;
		public static final int SELECT_CATEGORY = 11;
	}

	public static class MatchType {
		public static final int WITH_TEXT = 0;
		public static final int WITH_ID = 1;
		public static final int CONTAINS_TEXT = 2;
	}

	public static class MatchResult {
		public static final int IS_DISPLAYED = 0;
		public static final int NOT_DISPLAYED = 1;
	}
}
