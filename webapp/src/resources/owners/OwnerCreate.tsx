import { Stack } from "@mui/material";
import { Create, Form, SaveButton, TextInput } from "react-admin";

export const OwnerCreate = () => (
  <Create>
    <Form>
      <Stack>
        <TextInput source="firstName" />
        <TextInput source="lastName" />
        <TextInput source="address" />
        <TextInput source="city" />
        <TextInput source="telephone" />
        <SaveButton />
      </Stack>
    </Form>
  </Create>
);
