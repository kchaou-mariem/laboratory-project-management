import Badge from 'react-bootstrap/Badge';
import React from 'react';
//import Stack from 'react-bootstrap/Stack';

function PillExample({name}) {
var el={
name : "A venir",
color : "secondary"
};
  switch(name){
   case "EN_COURS" :
  el={
name : "en_cours",
color : "primary"
};
break;
case "VALIDE" :
  el={
name : "valide",
color : "success"
};
break;
case "TERMINE" :
  el={
name : "termine",
color : "danger"
};
break;
case "REJETE" :
  el={
name : "rejete",
color : "danger"
};
break;
case "EN_ATTENTE" :
  el={
name : "en_attente",
color : "warning"
};
break;
  }
 
  return (
      <Badge pill bg={el.color}>
        {el.name}
      </Badge>
  );
}

export default PillExample;