
import { UserDetails } from "../userdetails/user-details";

export class LoginResponse {
    userDto: UserDetails | undefined;
    token : string | undefined
}
