package au.com.wow.codetestapp.util;

import com.google.gson.Gson;

/**
 * @FileName JsonHelper.java
 * @PurposeJson Helper class. This class helps to serialize and deserialize
 *              objects.
 * @RevisionHistory Created
 */
public class JsonHelperUtils {

	/**
	 * Serialises the object to json
	 *
	 * @param objectToSerialize
	 * @return
	 */
	public synchronized static String serialize(Object objectToSerialize) {
		return new Gson().toJson(objectToSerialize);
	}

	/**
	 * Deserialises the Json to Object.
	 *
	 * @param returnType
	 * @param jsonData
	 * @return
	 */
	public synchronized static <T> T deSerialize(Class<T> returnType,
			String jsonData) {
		return new Gson().fromJson(jsonData, returnType);
	}


}