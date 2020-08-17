package hmac;


import com.google.common.io.BaseEncoding;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Main {

    private final static String HMAC_SHA256_ALGORITHM = "HmacSHA256";
    private final static Charset C_UTF8 = Charset.forName("UTF8");

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {

        String string = "billingAddress.city:billingAddress.country:billingAddress.houseNumberOrName:billingAddress.postalCode:billingAddress.stateOrProvince:billingAddress.street:billingAddressType:brandCode:countryCode:currencyCode:deliveryAddress.city:deliveryAddress.country:deliveryAddress.houseNumberOrName:deliveryAddress.postalCode:deliveryAddress.stateOrProvince:deliveryAddress.street:deliveryAddressType:merchantAccount:merchantReference:openinvoicedata.line1.currencyCode:openinvoicedata.line1.description:openinvoicedata.line1.itemAmount:openinvoicedata.line1.itemVatAmount:openinvoicedata.line1.itemVatPercentage:openinvoicedata.line1.lineReference:openinvoicedata.line1.numberOfItems:openinvoicedata.line1.vatCategory:openinvoicedata.line2.currencyCode:openinvoicedata.line2.description:openinvoicedata.line2.itemAmount:openinvoicedata.line2.itemVatAmount:openinvoicedata.line2.itemVatPercentage:openinvoicedata.line2.lineReference:openinvoicedata.line2.numberOfItems:openinvoicedata.line2.vatCategory:openinvoicedata.numberOfLines:openinvoicedata.refundDescription:paymentAmount:resURL:sessionValidity:shipBeforeDate:shopper.dateOfBirthDayOfMonth:shopper.dateOfBirthMonth:shopper.dateOfBirthYear:shopper.firstName:shopper.gender:shopper.lastName:shopper.telephoneNumber:shopperEmail:shopperIP:shopperLocale:shopperReference:skinCode:Gießen:DE:43:39440::LudwigStraßer:1:klarna:DE:EUR:Gießen:DE:43:39440::LudwigStraßer:1:ShopgateTestCOM:1700030366:EUR:\\\"Look At My Apps\\\" Men T-shirt L Black:1000:0:0:1:3:High:EUR:Shipping costs:499:0:0:2:1:None:2:SG1700030366:3499:http\\://testshop.obilenko.localdev.cc/php/shopgate/payment/generic_visit_return/5700030366/7dfd1c6e20f515a8a7b92337d238694ca96821e80302fe75d70a10750144c7fa:2017-03-17T15\\:53\\:06+00\\:00:2017-03-17:01:01:1969:Adrian:MALE:Smith:0123456789:selenium+1489387773653@shopgate.com:10.0.76.229:en:30037484:4lPFEMm1";

        String expected = "5NlUpM2uDS/gbkyr0PLT0q/jChNH2KJjP3BhmjdVyRU=";
        String actual = calculateHMAC(string, "5FE651FAD91574A92BF65C7CD99A367576EF2ABCEAC72C5DD7EEF78614010CAF");

        System.out.println(expected);
        System.out.println(actual);

    }

    // To calculate the HMAC SHA-256
    public static String calculateHMAC(String data, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] rawKey = BaseEncoding.base16().decode(key);
        // Create an hmac_sha256 key from the raw key bytes
        SecretKeySpec signingKey = new SecretKeySpec(rawKey, HMAC_SHA256_ALGORITHM);

        // Get an hmac_sha256 Mac instance and initialize with the signing
        // key
        Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);

        mac.init(signingKey);

        // Compute the hmac on input data bytes
        byte[] rawHmac = mac.doFinal(data.getBytes(C_UTF8));

        // Base64-encode the hmac
        return BaseEncoding.base64().encode(rawHmac);
    }
}
