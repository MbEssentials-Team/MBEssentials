package plugins.mbes.managers;

import java.util.HashMap;
import java.util.Map;

public class ChatReplacer {
	private Map<String,String>words = new HashMap<String, String>();
	
	public Map<String,String> getMap(){
		return words;
	}
	 
	/**
	 * Add a word to replace in the global chat
	 * 
	 * @param rp - Word to replace
	 * @param tr - Word/Words to replace with
	 */
	public void addWord(String rp,String tr){
		words.put(rp,tr);
	}
	
}
