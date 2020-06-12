## Lateral Navigation:
 * Lateral navigation is **navigation between sibling screens in the application's screen hierarchy** (sometimes referred to as a screen map). The most prominent lateral navigation patterns are tabs and horizontal paging (also known as swipe views). This pattern and others are described in Designing Effective Navigation. **For Example Tab Navigation**
    
    * Between siblings
    * From a list of stories to a list in a different tab
    * From story to story under the same tab
    
    <img src="https://github.com/Muneiahtellakula/android_development/blob/master/latralNav.JPG">
### Benefits of using tabs and swipes
* A single, initially-selected tab—users have access to content without further navigation
* Navigate between related screens without visiting parent
### Best practices with tabs
* Layout horizontally
* Run along top of screen
* Persistent across related screens
 Switching should not be treated as history


### Tab Navigation
   

* Here we will see how to use another of the design library components, the TabLayout, which can be combined with the ViewPager class to create a tab based interface within an Android activity.


#### An Introduction to the ViewPager
* Although not part of the design support library, the ViewPager is a useful companion class when used in conjunction with the TabLayout component to implement a tabbed user interface. The primary role of the ViewPager is to allow the user to flip through different pages of information where each page is most typically represented by a layout fragment. The fragments that are associated with the ViewPager are managed by an instance of the FragmentPagerAdapter class.

* At a minimum the pager adapter assigned to a ViewPager must implement two methods. The first, named getCount(), must return the total number of page fragments available to be displayed to the user. The second method, getItem(), is passed a page number and must return the corresponding fragment object ready to be presented to the user.

#### An Overview of the TabLayout Component

* TabLayout is one of the components introduced as part of material design and is included in the design support library. The purpose of the TabLayout is to present the user with a row of tabs which can be selected to display different pages to the user. 
* The tabs can be fixed or scrollable, whereby the user can swipe left or right to view more tabs than will currently fit on the display. 
* The information displayed on a tab can be text-based, an image or a combination of text and images.like below fig:

<img src="https://github.com/Muneiahtellakula/android_development/blob/master/navbar.JPG">


## Steps for implementing tabs

1. Define the tab layout using TabLayout
2. Implement a fragment and its layout for each tab
3. Implement a PagerAdapter from FragmentPagerAdapter  or FragmentStatePagerAdapter
4. Create an instance of the tab layout
5. Manage screen views in fragments
6. Set a listener to determine which tab is tapped

### Let's Start with Practical 

### Creating the TabLayoutDemo Project

1. Create a new project in Android Studio, entering TabLayoutDemo into the Application name field and package as the Company Domain setting before clicking on the Next button.

2. On the form factors screen, enable the Phone and Tablet option and set the minimum SDK setting to API 14: Android 4.0 (IceCreamSandwich).
3. Continue through the configuration screens requesting the creation of a **Empty Activity** named **TabLayoutDemoActivity** with a corresponding layout file named activity_tab_layout_demo. Click on the Finish button to initiate the project creation process.

4. Once the project has been created, load the content_tab_layout_demo.xml file into the Layout Editor tool, select “Hello World” TextView object, and then delete it.
5. Add the below dependancy at build.gradle(app:Module)
```
    implementation 'com.android.support:design:28.0.0'

```
#### Creating the First Fragment
* Each of the tabs on the TabLayout will display a different fragment when selected. Create the first of these fragments by right-clicking on the app -> java -> your package entry in the Project tool window and selecting the New -> Fragment -> Fragment (Blank) option. In the resulting dialog, enter Tab1Fragment into the Fragment Name: field and fragment_tab1 into the Fragment Layout Name: field. Enable the Create layout XML? option and disable both the Include fragment factory methods? and **Uncheck Include interface callbacks?** options before clicking on the Finish button to create the new fragment:Follow fig:

<img src="https://github.com/Muneiahtellakula/android_development/blob/master/frg_1.JPG">

**The files like below :Same like above process to create another two fragments**

<img src="https://github.com/Muneiahtellakula/android_development/blob/master/pjt_structre.JPG">

#### activity.xml file

``` xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    android:orientation="vertical"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">
    
<com.google.android.material.tabs.TabLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:tabTextColor="@android:color/white"
    android:id="@+id/tab_layout"
    android:background="@color/colorPrimary"
    />
    <androidx.viewpager.widget.ViewPager
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/viewPage"/>

</LinearLayout>

```

#### MainActivity.java file

```Java
package com.muneiah.tablayoutdemoactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

   //Inisilize the views
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //connect the view id's from layout
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewPage);
        //for viewpager set the adapter
        viewPager.setAdapter(new MyFragmentViewPager(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        //tab selcet listenter
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(MainActivity.this, "selected " + tab.getText().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Toast.makeText(MainActivity.this, "un-selected " + tab.getText().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Toast.makeText(MainActivity.this, "re-selected " + tab.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    //Creating new Inner classs for adapter
    public class MyFragmentViewPager extends FragmentPagerAdapter {
        String titles[] = {"Chats","Status","Calls"};

        public MyFragmentViewPager(@NonNull FragmentManager fm) {
            super(fm);
        }

        //getting tabs/fragments possions
        @NonNull
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new ChatFragment();
            }
            if (position == 1) {
                return new StatusFragment();
            }
            if (position == 2) {
                return new CallsFragment();
            }
            return null;
        }

        //size of the tabs
        @Override
        public int getCount() {
            return titles.length;
        }

        //for tabpage titiles
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}


```
#### fragment_chart.xml

```xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    android:orientation="vertical"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".ChatFragment">

    <!-- TODO: Update blank fragment layout -->
    <TextView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:text="Chat Fragment"
        android:gravity="center"
        android:textSize="30sp"/>

</LinearLayout>

```

#### Chat_fragment.java

```java
package com.muneiah.tablayoutdemoactivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }
}


````

**same remaining two fragments**

## Run the App

## Output Screen

<img src="https://github.com/Muneiahtellakula/android_development/blob/master/tabNavPractical.gif">

		
## Happy Coding :sunglasses:
				
## Thank You..!


