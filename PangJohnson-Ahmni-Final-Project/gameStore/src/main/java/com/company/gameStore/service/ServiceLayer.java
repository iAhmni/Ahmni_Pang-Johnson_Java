package com.company.gameStore.service;

import com.company.gameStore.models.*;
import com.company.gameStore.repositories.*;
import com.company.gameStore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceLayer {
    private InvoiceRepository invoiceRepository;
    private GameRepository gameRepository;
    private ConsoleRepository consoleRepository;
    private TShirtRepository tshirtRepository;
    private FeeRepository feeRepository;
    private TaxRepository taxRepository;

    @Autowired
    public ServiceLayer(InvoiceRepository invoiceRepository, GameRepository gameRepository,
                        ConsoleRepository consoleRepository, TShirtRepository tshirtRepository,
                        FeeRepository feeRepository, TaxRepository taxRepository) {
        this.invoiceRepository = invoiceRepository;
        this.gameRepository = gameRepository;
        this.consoleRepository = consoleRepository;
        this.tshirtRepository = tshirtRepository;
        this.feeRepository = feeRepository;
        this.taxRepository = taxRepository;
    }

    public InvoiceViewModel saveInvoice(InvoiceViewModel invoiceViewModel) {

        int requestedQuantity = invoiceViewModel.getQuantity();
        String requestedState = invoiceViewModel.getState();

        // Checks that order quantity is more than 0
        if (requestedQuantity <= 0) {
            throw new IllegalArgumentException("Quantity value has to be more than 0.");
        }

        // Checks that state code is valid
        List<Tax> states = taxRepository.findAll();
        List<String> stateCodes = new ArrayList<>();
        for (Tax taxObject: states) {
            String stateCode = taxObject.getState();
            stateCodes.add(stateCode);
        }
        if (!stateCodes.contains(requestedState)) {
            throw new IllegalArgumentException("Provided state code is invalid.");
        }

        // Checks that item type is valid
        if (invoiceViewModel.getItemType().equals("Game")) {
            Optional<Game> gameContainer = gameRepository.findById(invoiceViewModel.getItemId());

            // Checks that game ID is valid
            if (gameContainer.isPresent()) {
                Game game = gameContainer.get();
                int gameQuantity = game.getQuantity();

                // Checks that order quantity is available
                if (requestedQuantity <= gameQuantity) {
                    double unitPrice = game.getPrice();
                    double subtotal = getSubtotal(unitPrice, invoiceViewModel);
                    double salesTax = getSalesTax(subtotal, invoiceViewModel);
                    double processingFee = getProcessingFee(invoiceViewModel);
                    double totalPrice = getTotalPrice(subtotal, salesTax, processingFee, invoiceViewModel);

                    createInvoice(unitPrice, subtotal, salesTax, processingFee, totalPrice, invoiceViewModel);

                    // Update the game quantity
                    game.setQuantity(gameQuantity - requestedQuantity);
                    gameRepository.save(game);

                    return invoiceViewModel;

                } else {
                    throw new IllegalArgumentException("Order quantity not available.");
                }

            } else {
                throw new IllegalArgumentException("Provided Game ID is invalid.");
            }

        } else if (invoiceViewModel.getItemType().equals("T-Shirt")) {
            Optional<TShirt> tshirtContainer = tshirtRepository.findById(invoiceViewModel.getItemId());

            // Checks that tshirt ID is valid
            if (tshirtContainer.isPresent()) {
                TShirt tshirt = tshirtContainer.get();
                int tshirtQuantity = tshirt.getQuantity();

                // Checks that order quantity is available
                if (requestedQuantity <= tshirtQuantity) {
                    double unitPrice = tshirt.getPrice().doubleValue();
                    double subtotal = getSubtotal(unitPrice, invoiceViewModel);
                    double salesTax = getSalesTax(subtotal, invoiceViewModel);
                    double processingFee = getProcessingFee(invoiceViewModel);
                    double totalPrice = getTotalPrice(subtotal, salesTax, processingFee, invoiceViewModel);

                    createInvoice(unitPrice, subtotal, salesTax, processingFee, totalPrice, invoiceViewModel);

                    // Update the tshirt quantity
                    tshirt.setQuantity(tshirtQuantity - requestedQuantity);
                    tshirtRepository.save(tshirt);

                    return invoiceViewModel;

                } else {
                    throw new IllegalArgumentException("Order quantity not available.");
                }

            } else {
                throw new IllegalArgumentException("Provided Tshirt ID is invalid.");
            }

        } else if (invoiceViewModel.getItemType().equals("Console")) {
            Optional<Console> consoleContainer = consoleRepository.findById(invoiceViewModel.getItemId());

            // Checks that console ID is valid
            if (consoleContainer.isPresent()) {
                Console console = consoleContainer.get();
                int consoleQuantity = console.getQuantity();

                // Checks that order quantity is available
                if (requestedQuantity <= consoleQuantity) {
                    double unitPrice = console.getPrice().doubleValue();
                    double subtotal = getSubtotal(unitPrice, invoiceViewModel);
                    double salesTax = getSalesTax(subtotal, invoiceViewModel);
                    double processingFee = getProcessingFee(invoiceViewModel);
                    double totalPrice = getTotalPrice(subtotal, salesTax, processingFee, invoiceViewModel);

                    createInvoice(unitPrice, subtotal, salesTax, processingFee, totalPrice, invoiceViewModel);

                    // Update the console quantity
                    console.setQuantity(consoleQuantity - requestedQuantity);
                    consoleRepository.save(console);

                    return invoiceViewModel;

                } else {
                    throw new IllegalArgumentException("Order quantity not available.");
                }

            } else {
                throw new IllegalArgumentException("Provided Console ID is invalid.");
            }

        } else {
            throw new IllegalArgumentException("Provided item type is invalid.");
        }

    }

    public Invoice getInvoice(int id){
        Optional<Invoice> invoiceContainer = invoiceRepository.findById(id);

        return invoiceContainer.orElse(null);
    }

    public List<Invoice> getInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice getByName(String name) {
        List<Invoice> invoices = invoiceRepository.findByName(name);

        return invoices.get(0);
    }

    private void createInvoice(double unitPrice, double subtotal, double salesTax, double processingFee, double totalPrice, InvoiceViewModel invoiceViewModel) {

        Invoice newInvoice = new Invoice();
        newInvoice.setName(invoiceViewModel.getName());
        newInvoice.setStreet(invoiceViewModel.getStreet());
        newInvoice.setCity(invoiceViewModel.getCity());
        newInvoice.setState(invoiceViewModel.getState());
        newInvoice.setZipcode(invoiceViewModel.getZipcode());
        newInvoice.setItemType(invoiceViewModel.getItemType());
        newInvoice.setItemId(invoiceViewModel.getItemId());
        newInvoice.setQuantity(invoiceViewModel.getQuantity());
        newInvoice.setUnitPrice(BigDecimal.valueOf(unitPrice));
        newInvoice.setSubtotal(BigDecimal.valueOf(subtotal));
        newInvoice.setTax(BigDecimal.valueOf(salesTax));
        newInvoice.setProcessingFee(BigDecimal.valueOf(processingFee));
        newInvoice.setTotal(BigDecimal.valueOf(totalPrice));

        invoiceRepository.save(newInvoice);
    }

    private double getTotalPrice(double subtotal, double salesTax, double processingFee, InvoiceViewModel invoiceViewModel) {
        double totalPrice;

        if (invoiceViewModel.getQuantity() > 10) {
            double additionalProcessingFee = 15.49;
            totalPrice = subtotal + salesTax + processingFee + additionalProcessingFee;

        } else {
            totalPrice = subtotal + salesTax + processingFee;
        }

        totalPrice = round(totalPrice, 2);

        return totalPrice;

    }

    private double getProcessingFee(InvoiceViewModel invoiceViewModel) {
        List<Fee> fees = feeRepository.findByProductType(invoiceViewModel.getItemType());
        Fee feeObject = fees.get(0);

        return feeObject.getFee().doubleValue();
    }

    private double getSalesTax(Double subtotal, InvoiceViewModel invoiceViewModel) {
        List<Tax> taxRates = taxRepository.findByState(invoiceViewModel.getState());
        Tax taxObject = taxRates.get(0);
        double taxRate = taxObject.getRate().doubleValue();
        double salesTax = subtotal * taxRate;
        salesTax = round(salesTax, 2);

        return salesTax;

    }

    private double getSubtotal(Double unitPrice, InvoiceViewModel invoiceViewModel) {
        double subtotal = unitPrice * invoiceViewModel.getQuantity();
        subtotal = round(subtotal, 2);

        return subtotal;
    }

    // https://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
