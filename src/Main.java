import java.util.Scanner;

interface PaymentOption {
    void processPayment(double amount);
}

// Klassen voor betaalmethodes //
class IdealPayment implements PaymentOption {
    @Override
    public void processPayment(double amount) {
        System.out.println("Verwerk betaling via iDEAL voor €" + amount);
    }
}

class QRCodePayment implements PaymentOption {
    @Override
    public void processPayment(double amount) {
        System.out.println("Genereer QR-code voor betaling van €" + amount);
    }
}

// Klasse voor betaalverzoek //
class PaymentRequest {
    private double amount;
    private PaymentOption paymentOption;

    public PaymentRequest(double amount, PaymentOption paymentOption) {
        this.amount = amount;
        this.paymentOption = paymentOption;
    }

    public void sendRequest() {
        System.out.println("Betaalverzoek voor €" + amount + " wordt verwerkt...");
        paymentOption.processPayment(amount);
    }
}

// In deze klasse wordt alles processen van dit programma op gang gezet // 
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Voer het bedrag in:");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Nieuwe regel maken //

        System.out.println("Kies betaaloptie (typ exact): iDeal of QR");
        String optie = scanner.nextLine(); // Keuze van gebruiker //

        PaymentOption paymentOption;

        switch (optie) {
            case "iDeal":
            paymentOption = new IdealPayment();
            break;
        case "QR":
            paymentOption = new QRCodePayment();
            break;
        default:
            System.out.println("Ongeldige optie");
            scanner.close();
            return;
        }

        PaymentRequest request = new PaymentRequest(amount, paymentOption);
        request.sendRequest();
    }
}


