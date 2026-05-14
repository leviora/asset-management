package pl.school.assetmanagement.application.port.out;

public interface EmailSender {

    void sendBrokenAssetNotification(String assetSerialNumber);
}
