package nelsonapps.demos.seniorApp.constants;

import java.util.Arrays;
import java.util.Optional;

public enum ColumnMapping {
	IBGE_ID("ibge_id","ibgeId"),
	UF("uf","uf"),
	NAME("name","name"),
	CAPITAL("captial","capital"),
	LON("lon","lon"),
	LAT("lat","lat"),
	NO_ACCENTS("no_accents","noAccents"),
	ALTERNATIVE_NAMES("alternative_names","alternativeNames"),
	MICROREGION("microregion","microregion"),
	MACROREGION("macroregion","macroregion");
	
	
	private final String columnNameFile;
	private final String columnNameDB;
	
	ColumnMapping(String columnNameFile,String columnNameDB){
		this.columnNameFile = columnNameFile;
		this.columnNameDB = columnNameDB;
	}
	
	public String getColumnNameFile(){
		return this.columnNameFile;
	}
	
	public String getColumnNameDB(){
		return this.columnNameDB;
	}
	
	public static Optional<ColumnMapping> findByColumnNameFile(String columnNameFile){
		
		return Arrays.asList(ColumnMapping.values())
		      .stream()
		      .filter(match-> match.columnNameFile.equals(columnNameFile))
		      .findFirst();
		
		
		/*switch(columnNameFile){
		case "ibge_id":
			return IBGE_ID;
		case "uf":
			return UF;
		case "name":
			return NAME;
		case "capital":
			return CAPITAL;
		case "lon":
			return LON;
		case "lat":
			return LAT;
		case "no_accents":
			return NO_ACCENTS;
		case "alternative_names":
			return ALTERNATIVE_NAMES;
		case "microregion":
			return MICROREGION;
		case "macroregion":
			return MACROREGION;
		default:
				return null;
			
		}*/
	}

}
