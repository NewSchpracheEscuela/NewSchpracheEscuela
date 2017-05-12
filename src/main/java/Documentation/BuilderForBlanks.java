package Documentation;

import Documentation.Enumerations.DocumentType;
import Documentation.Factories.Entities.BlankInfo;
import Documentation.Factories.IFactory;
import Documentation.Generators.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by angre on 12.05.2017.
 */
public class BuilderForBlanks {
    private IGenerator<BlankInfo> documentGenerator;

    private IFactory<BlankInfo> modelViewer;

    private final Map<DocumentType, IGenerator<BlankInfo>> defaultGenerators = new HashMap<DocumentType, IGenerator<BlankInfo>>() {{
        put(DocumentType.csvb, new CSVGeneratorBlank());
        put(DocumentType.pdfb, new PDFGeneratorBlank());
        put(DocumentType.xlsb, new XLSGeneratorBlank());
        put(DocumentType.csvd, new CSVGeneratorDiploma());
        put(DocumentType.pdfd, new PDFGeneratorDiploma());
        put(DocumentType.xlsd, new XLSGeneratorDiploma());
    }};

    private boolean isProtectedFromCopy = false;

    public BuilderForBlanks setProtectedFromCopy(boolean value) {
        this.isProtectedFromCopy = value;
        return this;

    }

    public BuilderForBlanks setDocumentType(DocumentType documentType) {
        setDocumentGenerator(defaultGenerators.get(documentType));
        return this;
    }

    public BuilderForBlanks setDocumentGenerator(IGenerator<BlankInfo> documentGenerator) {
        if (documentGenerator == null) throw new IllegalAccessError("generator is null");
        this.documentGenerator = documentGenerator;

        return this;
    }

    public BuilderForBlanks setModelViewer(IFactory<BlankInfo> modelViewer) {
        if (modelViewer == null) throw new IllegalAccessError("viewer is null");
        this.modelViewer = modelViewer;

        return this;

    }

    public void writeToResponse(List<BlankInfo> entityList, HttpServletResponse response) {
        if (documentGenerator == null) throw new IllegalAccessError("generator is null");
        if (modelViewer == null) throw new IllegalAccessError("viewer is null");

        documentGenerator.setIsProtected(isProtectedFromCopy);
        documentGenerator.setModelViewer(modelViewer);
        documentGenerator.writeToResponse(entityList, response);
    }
}
