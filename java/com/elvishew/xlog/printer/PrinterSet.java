package com.elvishew.xlog.printer;

/* loaded from: classes.dex */
public class PrinterSet implements Printer {
    private Printer[] printers;

    public PrinterSet(Printer... printerArr) {
        this.printers = printerArr;
    }

    @Override // com.elvishew.xlog.printer.Printer
    public void println(int i, String str, String str2) {
        for (Printer printer : this.printers) {
            printer.println(i, str, str2);
        }
    }
}
