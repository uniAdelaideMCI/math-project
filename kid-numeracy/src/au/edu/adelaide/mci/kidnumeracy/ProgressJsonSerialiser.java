package au.edu.adelaide.mci.kidnumeracy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class ProgressJsonSerialiser implements ProgressSerialiser {
	private Context mContext;
	private String mFileName;

	public ProgressJsonSerialiser(Context context, String fileName){
		mContext = context;
		mFileName = fileName;
	}
	@Override
	public void saveProgress(Progress progress) throws JSONException, IOException {
		Writer writer = null;
		try {
			OutputStream outputStream = mContext.openFileOutput(mFileName, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(outputStream);
			writer.write(progress.toJSON().toString());
		} finally {
			if (writer != null){
				writer.close();
			}
		}
		
	}

	@Override
	public Progress loadProgress() throws IOException, JSONException {
		BufferedReader reader = null;
		JsonProgress jsonProgress = null;
		
		InputStream in = mContext.openFileInput(mFileName);
		try{
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder jsonString = new StringBuilder();
			String line = null;
			while( (line = reader.readLine()) != null ){
				jsonString.append(line);
			}
			JSONObject jsonObject = new JSONObject(jsonString.toString());
			jsonProgress = new JsonProgress(jsonObject);
		}finally{
			if (reader != null){
				reader.close();
			}
		}
		
		return jsonProgress;
	}

}
