import {
  Datagrid,
  List,
  TextField,
  TextInput,
  WithRecord
} from "react-admin";

const ownerFilters = [<TextInput label="Search" source="q" alwaysOn />];

const petsRender = (owner: any) => {
  let petsPresentation = owner?.pets.map((pet: any) => pet.name).join(", ");
  return <p>{petsPresentation}</p>;
};

export const OwnerList = () => {
  return (
    <List resource="owners" filters={ownerFilters}>
      <Datagrid rowClick="show">
        <TextField source="firstName" />
        <TextField source="lastName" />
        <TextField source="address" />
        <TextField source="city" />
        <TextField source="telephone" />
        <WithRecord label="Pets" render={petsRender} />
      </Datagrid>
    </List>
  );
};
