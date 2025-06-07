package eai.devass.sinistreat.appli.valueobjects.metier.sinistre;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.utils.entites.IDate;

public class AlgoPoliceVO extends PoliceVO  implements Comparable  {
	      private SimpleDateFormat dateFormat = new SimpleDateFormat(
			IDate.FORMAT_SIMPLE);
    private static final Logger logger = Logger
            .getLogger("loggerSinATAlgoPoliceVOClass");
	      
	@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
        result = prime * result
                + ((dateOccurence == null) ? 0 : dateOccurence.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			AlgoPoliceVO other = (AlgoPoliceVO) obj;
			if (dateOccurence == null) {
				if (other.dateOccurence != null)
					return false;
			} else if (!dateOccurence.equals(other.dateOccurence))
				return false;
			return true;
		}

	public int compareTo(Object o) {
		 AlgoPoliceVO police =(AlgoPoliceVO)o; 
		 Date date0=new Date();
		 Date date1=new Date();
		try {
			 date0 = dateFormat.parse(dateOccurence);
			 date1 = dateFormat.parse(police.getDateOccurence());
		} catch (ParseException e) {
            logger.error("problème technique", e);
		}
		return date1.compareTo(date0);
	}
	
}