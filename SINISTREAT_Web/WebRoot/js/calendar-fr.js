// ** I18N

// Calendar EN language
// Author: Mihai Bazon, <mishoo@infoiasi.ro>
// Encoding: any
// Distributed under the same terms as the calendar itself.

// For translators: please use UTF-8 if possible.  We strongly believe that
// Unicode is the answer to a real internationalized world.  Also please
// include your contact information in the header, as can be seen above.

// full day names
Calendar._DN = new Array
("Dimanche",
 "Lundi",
 "Mardi",
 "Mercredi",
 "Jeudi",
 "Vendredi",
 "Samedi");

// Please note that the following array of short day names (and the same goes
// for short month names, _SMN) isn't absolutely necessary.  We give it here
// for exemplification on how one can customize the short day names, but if
// they are simply the first N letters of the full name you can simply say:
//
//   Calendar._SDN_len = N; // short day name length
//   Calendar._SMN_len = N; // short month name length
//
// If N = 3 then this is not needed either since we assume a value of 3 if not
// present, to be compatible with translation files that were written before
// this feature.

// short day names
Calendar._SDN = new Array
("Dim",
 "Lun",
 "Mar",
 "Mer",
 "Jeu",
 "Ven",
 "Sam");

// full month names
Calendar._MN = new Array
("Janvier",
 "Fevrier",
 "Mars",
 "Avril",
 "Mai",
 "Juin",
 "Juillet",
 "Aout",
 "Septembre",
 "Octobre",
 "Novembre",
 "Decembre");

// short month names
Calendar._SMN = new Array
("Jan",
 "Fev",
 "Mar",
 "Avr",
 "Mai",
 "Juin",
 "Juil",
 "Aout",
 "Sep",
 "Oct",
 "Nov",
 "Dec");

// tooltips
Calendar._TT = {};
Calendar._TT["INFO"] = "A propos du calendrier";

Calendar._TT["ABOUT"] =
"DHTML Date/Time Selector\n" +
"(c) dynarch.com 2002-2003\n" + // don't translate this this ;-)
"For latest version visit: http://dynarch.com/mishoo/calendar.epl\n" +
"Distributed under GNU LGPL.  See http://gnu.org/licenses/lgpl.html for details." +
"\n\n" +
"Date selection:\n" +
"- Use the \xab, \xbb buttons to select year\n" +
"- Use the " + String.fromCharCode(0x2039) + ", " + String.fromCharCode(0x203a) + " buttons to select month\n" +
"- Hold mouse button on any of the above buttons for faster selection.";
Calendar._TT["ABOUT_TIME"] = "\n\n" +
"Time selection:\n" +
"- Click on any of the time parts to increase it\n" +
"- or Shift-click to decrease it\n" +
"- or click and drag for faster selection.";

Calendar._TT["PREV_YEAR"] = "Annee prec. (maintenir pour menu)";
Calendar._TT["PREV_MONTH"] = "Mois prec. (maintenir pour menu)";
Calendar._TT["GO_TODAY"] = "Atteindre date du jour";
Calendar._TT["NEXT_MONTH"] = "Mois suiv. (maintenir pour menu)";
Calendar._TT["NEXT_YEAR"] = "Annee suiv. (maintenir pour menu)";
Calendar._TT["SEL_DATE"] = "Choisir une date";
Calendar._TT["DRAG_TO_MOVE"] = "Deplacer";
Calendar._TT["PART_TODAY"] = " (Aujourd'hui)";
Calendar._TT["MON_FIRST"] = "Commencer par lundi";
Calendar._TT["SUN_FIRST"] = "Commencer par dimanche";

// the following is to inform that "%s" is to be the first day of week
// %s will be replaced with the day name.
Calendar._TT["DAY_FIRST"] = "Commencer par %s";

// This may be locale-dependent.  It specifies the week-end days, as an array
// of comma-separated numbers.  The numbers are from 0 to 6: 0 means Sunday, 1
// means Monday, etc.
Calendar._TT["WEEKEND"] = "0,6";

Calendar._TT["CLOSE"] = "Fermer";
Calendar._TT["TODAY"] = "Aujourd'hui";
Calendar._TT["TIME_PART"] = "(Shift-)Click or drag to change value";

// date formats
Calendar._TT["DEF_DATE_FORMAT"] = "%d-%m-%y";
Calendar._TT["TT_DATE_FORMAT"] = "%A %e %B %Y";

Calendar._TT["WK"] = "sem";
Calendar._TT["TIME"] = "Date:";
