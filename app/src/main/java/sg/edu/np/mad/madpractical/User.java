package sg.edu.np.mad.madpractical;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    public String name;
    public String description;
    public int id;
    public boolean followed;

    public User(String name, String description, int id, boolean followed) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.followed = followed;
    }

    protected User(Parcel in) {
        name = in.readString();
        description = in.readString();
        id = in.readInt();
        followed = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public void ToggleFollow() {
        followed = !followed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeInt(id);
        parcel.writeByte((byte) (followed ? 1 : 0));
    }
}
