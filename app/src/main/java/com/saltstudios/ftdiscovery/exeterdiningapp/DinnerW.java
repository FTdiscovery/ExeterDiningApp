package com.saltstudios.ftdiscovery.exeterdiningapp;

/**
 * Created by Gordon on 26/11/16.
 */

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DinnerW extends Fragment {

    String server;
    public DinnerW(String s) {
        server = s;
    }

    public DinnerW() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dinner, container, false);
        //Storage Variables

        final ArrayList<String> foo = new ArrayList<String>();
        final ArrayList<Float> rate = new ArrayList<Float>();
        final ArrayList<Integer> tRate = new ArrayList<Integer>();
        final ArrayList<Integer> tAnon = new ArrayList<Integer>();
        final ArrayList<String> des = new ArrayList<String>();
        final ArrayList<Float> userLastR = new ArrayList<Float>();
        //display variables
        final float[] ratings = new float[foo.size()];
        final ListView listOfFood = (ListView) rootView.findViewById(R.id.dFood);
        final TextView textView = (TextView) rootView.findViewById(R.id.excuse);
        final CustomAdapter foodAdapter = new CustomAdapter(getActivity(),foo,rate,tRate);
        //final ArrayAdapter<String> foodAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,foo);
        listOfFood.setAdapter(foodAdapter);
        Firebase.setAndroidContext(getActivity());
        Firebase sadServer = new Firebase(server);
        Firebase sadServerNames = sadServer.child("dinnerWName");
        sadServerNames.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String message = dataSnapshot.getValue(String.class);
                foo.add(message);
                textView.setText("");
                userLastR.add((float) 0);
                foodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String message = dataSnapshot.getValue(String.class);
                foo.add(message);
                userLastR.add((float) 0);
                foodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
            Firebase cheekyExcuse = sadServer.child("excuseW");
            cheekyExcuse.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String message = dataSnapshot.getValue(String.class);
                    textView.setText(message);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        final Firebase sadServerDescriptions = sadServer.child("dinnerWDescription");
        sadServerDescriptions.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String message = dataSnapshot.getValue(String.class);
                des.add(message);
                foodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String message = dataSnapshot.getValue(String.class);
                des.add(message);
                foodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        final Firebase sadServerRatings = sadServer.child("dinnerWRatings");
        sadServerRatings.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Float num = dataSnapshot.getValue(Float.class);
                rate.add(num);
                foodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Float num = dataSnapshot.getValue(Float.class);
                rate.add(num);
                foodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        final Firebase sadServerTotalRatings = sadServer.child("dinnerWTotalRatings");
        sadServerTotalRatings.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int num = dataSnapshot.getValue(int.class);
                tRate.add(num);
                foodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                int num = dataSnapshot.getValue(int.class);
                tRate.add(num);
                foodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        Firebase sadServerTotalAnon = sadServer.child("dinnerWTotalAnon");
        sadServerTotalAnon.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int num = dataSnapshot.getValue(int.class);
                tAnon.add(num);
                foodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                int num = dataSnapshot.getValue(int.class);
                tAnon.add(num);
                foodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        for(int i = 0;i<foo.size();i++) {
            ratings[i]=rate.get(i);
            foodAdapter.notifyDataSetChanged();
        }
        listOfFood.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                        AlertDialog.Builder ab;
                        ab = new AlertDialog.Builder(getActivity());
                        final String foodDescription = foo.get(position).toUpperCase();
                        ab.setTitle("INFO: " + foodDescription);
                        String describeFood="NO COMMENTS YET";
                        if(des.get(position).length()>0) describeFood = des.get(position);
                        float tempRate = rate.get(position);
                        DecimalFormat dec = new DecimalFormat("0.00");
                        String rateDis = dec.format(tempRate);
                        ab.setMessage("Comments from your edgy friends:\n" + describeFood + "\n\nRATING: " + rateDis + "\n\nTotal user submitted ratings (get a life): " + tRate.get(position));

                        final EditText et = new EditText(getActivity());
                        et.setHint("psst. enter # between 1-5.");

                        ab.setView(et);

                        ab.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    float input = Float.parseFloat(et.getText().toString());
                                    if (input <= 5 && input >= 1) {
                                        if(userLastR.get(position) == 0) {
                                            float totalVal = tRate.get(position) * rate.get(position);
                                            tRate.set(position, tRate.get(position) + 1);
                                            rate.set(position, ((totalVal + input) / tRate.get(position)));
                                            userLastR.set(position, input);
                                        }
                                        else {
                                            float totalVal = (tRate.get(position) * rate.get(position)) - userLastR.get(position);
                                            rate.set(position, ((totalVal + input) / tRate.get(position)));
                                            userLastR.set(position, input);
                                            Toast.makeText(getActivity(), "Overrode previous rating.", Toast.LENGTH_SHORT).show();
                                        }
                                        //puts it back into the cheeky firebase.
                                        String positionAsString = String.valueOf(position+1);
                                        Firebase changePositionRating = sadServerRatings.child(positionAsString);
                                        Firebase changePositionTotalRatings = sadServerTotalRatings.child(positionAsString);
                                        changePositionRating.setValue(rate.get(position));
                                        changePositionTotalRatings.setValue(tRate.get(position));

                                        listOfFood.invalidateViews();
                                    } else {
                                        if (input > 5) {
                                            Toast.makeText(getActivity(), "Invalid input! Your rating cannot be over 5.", Toast.LENGTH_SHORT).show();
                                        }
                                        if (input < 1) {
                                            Toast.makeText(getActivity(), "Invalid input! Your rating cannot be under 1.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } catch (Exception e) {
                                    Toast.makeText(getActivity(), "Don't test the system, child. ", Toast.LENGTH_SHORT).show();

                                }
                            }
                        }).setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setNegativeButton("COMMENT", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Look, we have to do it again!
                                AlertDialog.Builder ac = new AlertDialog.Builder(getActivity());
                                ac.setTitle("Commenting about: " + foo.get(position));
                                ac.setMessage("We get it, you're an edgy kid. Now write your comment about the food here. Bear in mind people will actually read what you write. That might come as a shock to you. Anyway, just write.");
                                LinearLayout layout = new LinearLayout(getActivity().getApplicationContext());
                                layout.setOrientation(LinearLayout.VERTICAL);
                                final EditText descrip = new EditText(getActivity());
                                descrip.setHint("Boring Opinion:");
                                final EditText name = new EditText(getActivity());
                                name.setHint("Name:");
                                layout.addView(name);
                                layout.addView(descrip);

                                ac.setView(layout);
                                ac.setPositiveButton("FINISHED", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String newComment = descrip.getText().toString();
                                        if (newComment.length() > 0) {
                                            Toast.makeText(getActivity(), "Thank you, your input is somewhat appreciated!", Toast.LENGTH_SHORT).show();
                                            if (des.get(position).length() <= 200) {
                                                String commenter;
                                                if (name.getText().toString().length() == 0) {
                                                    commenter = "Anonymous " + tAnon.get(position);
                                                    tAnon.set(position,(tAnon.get(position)+1));
                                                } else commenter = name.getText().toString();
                                                if (des.get(position).length() > 0)
                                                    des.set(position,(des.get(position) + "\n"+commenter + ": \"" + newComment+"\""));
                                                else
                                                    des.set(position,(commenter + ": \"" + newComment + "\""));
                                            } else {
                                                des.set(position,("\nAnonymous " + tAnon.get(position) + ": \"" + newComment+"\""));
                                            }
                                            String positionAsString = String.valueOf(position+1);
                                            Firebase changeDescription = sadServerDescriptions.child(positionAsString);
                                            changeDescription.setValue(des.get(position));
                                        }
                                        else Toast.makeText(getActivity(), "A constructive comment needs text", Toast.LENGTH_SHORT).show();
                                    }
                                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                AlertDialog a =ac.create();
                                a.show();

                            }
                        });
                        AlertDialog a = ab.create();
                        a.show();

                    }

                }
        );
        return rootView;
    }
}