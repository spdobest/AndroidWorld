package spm.androidworld.all.xmlParsing.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(strict = false, name = "ArrayOfObjStation")
public class StationObjectResponse {
    @ElementList(
            required = false,
            name = "objStation",
            entry = "objStation",
            inline = true,
            empty = true
    )
    ArrayList<ObjStationData> cardsSummaryList;

    public StationObjectResponse() {

    }

    public StationObjectResponse(@ElementList(required = false, name = "objStation") ArrayList<ObjStationData> cardsSummaryList) {
        this.cardsSummaryList = cardsSummaryList;
    }

    public ArrayList<ObjStationData> getCardsSummaryList() {
        return cardsSummaryList;
    }

    public void setCardsSummaryList(ArrayList<ObjStationData> cardsSummaryList) {
        this.cardsSummaryList = cardsSummaryList;
    }
}