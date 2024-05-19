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

export const App = () => {
  return (
    <Admin
      dataProvider={dataProvider}
      lightTheme={amplicodeLightTheme}
      darkTheme={amplicodeDarkTheme}
    >
      <Resource
        name="owners"
        list={ListGuesser}
        edit={EditGuesser}
        show={ShowGuesser}
        recordRepresentation="firstName"
      />
    </Admin>
  );
};
