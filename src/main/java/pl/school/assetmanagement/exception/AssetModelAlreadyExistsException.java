package pl.school.assetmanagement.exception;

public class AssetModelAlreadyExistsException extends RuntimeException {

    public AssetModelAlreadyExistsException(String manufacturer, String modelName) {
        super("Asset model already exists: " + manufacturer + " " + modelName);
    }
}
