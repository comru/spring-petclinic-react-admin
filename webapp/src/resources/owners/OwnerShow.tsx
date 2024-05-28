import {
  Datagrid,
  List,
  Show,
  SimpleShowLayout,
  TextField,
  useRecordContext,
} from "react-admin";

const PetsTable = () => {
  const record = useRecordContext();
  if (!record) {
    return <></>;
  }
  return (
    <List
      disableSyncWithLocation
      resource="pets"
      filter={{ ownerId: record.id }}
    >
      <Datagrid>
        <TextField source="name"/>
        <TextField source="birthDate"/>
      </Datagrid>
    </List>
  );
};

export const OwnerShow = () => (
  <Show>
    <SimpleShowLayout>
      <TextField source="id" />
      <TextField source="firstName" />
      <TextField source="lastName" />
      <TextField source="address" />
      <TextField source="city" />
      <TextField source="telephone" />
    </SimpleShowLayout>

    <PetsTable />
  </Show>
);
