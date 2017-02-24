/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Jpeg;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import controller.atributosMenuPrincipal;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DAO;

/**
 *
 * @author diego
 */
public class GerarNotaFiscal {

    public GerarNotaFiscal() {
        Document document = new Document();
        
        atributosMenuPrincipal novo = new atributosMenuPrincipal();
        
        DAO dao = new DAO();
        novo = dao.atualiza(null);
        
        
        
        try {
            PdfWriter.getInstance(document, new FileOutputStream("notafiscal_id_"+novo.getId_venda()+".pdf"));
            document.open();
            document.setPageSize(PageSize.A4);
            
            Paragraph titulo = new Paragraph("Nota fiscal eletrônica - Serralheria Sousa");
            titulo.setAlignment(400);
            Image logo = null;
            try {
                logo = Image.getInstance("/media/diego/HD1/Documentos/Sistemas de Informação/5º Período/Programação Orientada a Objetos II/Dados/Serralheria Sousa/img1.png");
                //logo.setAbsolutePosition(10, 10);
                
            
            } catch (BadElementException | IOException ex) {
                Logger.getLogger(GerarNotaFiscal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                     
           
            Paragraph dados = new Paragraph(""
                    + "\nID da venda: "+novo.getId_venda()+""
                    + "\nProduto: "+novo.getProduto()+""
                    + "\nQuantidade do produto: "+novo.getQtd()+""
                    + "\nValor da venda: R$ "+novo.getValor_venda()+""
                    + "\nCliente: "+novo.getNome_cliente()
                    +"\n\nData da venda: "+novo.getData()+""
                    + "\nVendedor: "+novo.getNome_usuario());
            
            
            document.add(logo);
            document.add(titulo);
            document.add(new Paragraph("___________________________________________________________________"));
            document.add(dados);
            document.add(new Paragraph("\n\n\n___________________________________________________________________"));
            document.add(new Paragraph("Serralheria Sousa LTDA© - Todos os direitos reservados\n" +
                        "\n" +
                        "Rua Sinhá Soido, 228\n" +
                        "Bairro: Oeiras Nova Cidade: Oeiras\n" +
                        "UF: PI\n" +
                        "CEP: 64500-000\n" +
                        "(89) 3462-2994"
                        + "\n\nhttp://www.serralheriasousa.pe.hu  senha: abacate"));
            //document.add(new );
        } catch (FileNotFoundException | DocumentException ex) {
            System.out.println("Erro"+ex);
        }finally{
            document.close();
        }
        
        try {
            Desktop.getDesktop().open(new File("notafiscal_id_"+novo.getId_venda()+".pdf"));
        } catch (IOException ex) {
            Logger.getLogger(GerarNotaFiscal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        new GerarNotaFiscal();
    }
        
    }
    
