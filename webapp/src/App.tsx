import {
  Admin,
  EditGuesser,
  ListGuesser,
  Resource,
  ShowGuesser,
} from "react-admin";
import {
  amplicodeDarkTheme,
  amplicodeLightTheme,
} from "./themes/amplicodeTheme/amplicodeTheme";
import { dataProvider } from "./dataProvider";
import { OwnerList } from "./resources/owners/OwnerList";
import { OwnerCreate } from "./resources/owners/OwnerCreate";
import { OwnerShow } from "./resources/owners/OwnerShow";

export const App = () => {
  return (
    <Admin
      dataProvider={dataProvider}
      lightTheme={amplicodeLightTheme}
      darkTheme={amplicodeDarkTheme}
    >
      <Resource
        name="owners"
        list={OwnerList}
        create={OwnerCreate}
        edit={EditGuesser}
        show={OwnerShow}
        recordRepresentation={(record) => `${record.firstName} ${record.lastName}`}
      />
    </Admin>
  );
};
