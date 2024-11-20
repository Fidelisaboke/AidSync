package com.example.aidsync.ui.firstaid

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstAidDetailsScreen(topic: String, navController: NavController) {
    // Define the steps for each topic
    val steps = when (topic) {
        "CPR" -> listOf(
            "1. Check if the person is responsive.",
            "2. Call for emergency help.",
            "3. Start chest compressions.",
            "4. Give rescue breaths.",
            "5. Continue CPR until help arrives."
        )
        "Bleeding" -> listOf(
            "1. Apply direct pressure to the wound.",
            "2. Elevate the injured area if possible.",
            "3. Apply a bandage to secure pressure.",
            "4. Seek medical attention."
        )
        "Burns" -> listOf(
            "1. Cool the burn with running cold water.",
            "2. Cover the burn with a clean, non-stick bandage.",
            "3. Seek medical attention for severe burns."
        )
        "Choking" -> listOf(
            "1. Perform the Heimlich maneuver or abdominal thrusts.",
            "2. Encourage the person to cough if they are able.",
            "3. Call for emergency help if the object is not cleared."
        )
        "Poisons" -> listOf(
            "Conscious Victim:",
                "1. Check Danger",
                "2. Clean up poison",
                "3. Avoid contaminating yourself",
                "4. Record poison details",
                "5. Seek medical assistance",

            "Unconscious Victim:",
                "1. Clear and open airway",
                "2. If victim is not breathing begin CPR",
                "3. Seek medical assistance"
        )
        "Wound Care" -> listOf(
            "1. Stop bleeding",
            "2. Wash hands and use gloves",
            "3. Clean wound with saline or clean water",
            "4. Apply and secure sterile dressing",
            "5. Seek medical help",
            "5. Dispose off gloves and dressings"
        )
        "Allergic Reaction" -> listOf(
            "1. Get the victim to be comfortable",
            "2. Recognize symptoms: swelling, difficulty breathing, rash, etc.",
            "3. Administer antihistamines if available.",
            "4. If severe, use an epinephrine injection (EpiPen).",
            "5. Call emergency services immediately if symptoms worsen."
        )
        "Asthma" -> listOf(
            "1. Help the person sit upright and stay calm.",
            "2. Assist with inhaler use (if prescribed).",
            "3. If symptoms don't improve, call for emergency medical help."
        )
        "Anaphylaxis" -> listOf(
            "1. Immediately administer epinephrine (EpiPen) if available.",
            "2. Call emergency services for further treatment.",
            "3. Keep the person lying down and elevate their legs, unless breathing is difficult."
        )
        "Diabetics" -> listOf(
            "1. If the person is conscious, give them a fast-acting sugar source (e.g., juice, glucose tablets).",
            "2. If unconscious, do not attempt to give food or liquid.",
            "3. Call emergency services if the person does not improve quickly."
        )
        "Drug Overdose" -> listOf(
            "1. Check for signs of overdose (e.g., confusion, difficulty breathing, unconsciousness).",
            "2. Call emergency services immediately.",
            "3. If the person is unconscious but breathing, place them in the recovery position.",
            "4. Do not attempt to make them vomit unless instructed by medical personnel."
        )
        "Eye Injury" -> listOf(
            "1. Rinse the eye with clean water to flush out any debris.",
            "2. If a chemical has entered the eye, rinse for at least 15 minutes.",
            "3. Do not rub the eye.",
            "4. Cover the eye with a sterile bandage and seek immediate medical attention."
        )
        "Electric Shock" -> listOf(
            "1. Do not touch the person if they are still in contact with the electrical source.",
            "2. Turn off the power or disconnect the source of electricity.",
            "3. If the person is unconscious, check for breathing and pulse.",
            "4. Perform CPR if needed and call emergency services."
        )
        "Fractures" -> listOf(
            "1. Do not attempt to move the person unless absolutely necessary.",
            "2. Immobilize the fractured area with a splint.",
            "3. Apply a cold compress to reduce swelling.",
            "4. Seek immediate medical help."
        )
        "Frostbite" -> listOf(
            "1. Move the person to a warmer environment.",
            "2. Gently warm the affected area using body heat (e.g., placing frostbitten fingers under armpits).",
            "3. Do not rub the affected area or use direct heat (e.g., hot water).",
            "4. Call emergency services if the frostbite is severe."
        )
        "Head Injury" -> listOf(
            "1. Monitor for symptoms of concussion (dizziness, confusion, nausea).",
            "2. Keep the person still and calm.",
            "3. Apply a cold compress to reduce swelling if there is a bump.",
            "4. Seek medical attention if the person loses consciousness or if symptoms worsen."
        )
        "Heart Condition" -> listOf(
            "1. Call emergency services immediately.",
            "2. Have the person chew an aspirin (if they are not allergic).",
            "3. Keep the person calm and seated.",
            "4. Perform CPR if the person stops breathing."
        )
        "Nosebleed" -> listOf(
            "1. Have the person sit up and lean forward slightly.",
            "2. Pinch the nostrils together for at least 5 minutes.",
            "3. Apply a cold compress to the back of the neck or the nose.",
            "4. If the bleeding continues for over 20 minutes, seek medical help."
        )
        "Seizure" -> listOf(
            "1. Clear the area of any objects that could cause injury.",
            "2. Place something soft (like a jacket) under the person's head.",
            "3. Do not hold the person down or put anything in their mouth.",
            "4. After the seizure ends, turn the person onto their side and monitor their breathing.",
            "5. Call for medical assistance if the seizure lasts more than 5 minutes."
        )
        "Shock" -> listOf(
            "1. Keep the person lying flat and raise their legs if possible.",
            "2. Cover them with a blanket to maintain warmth.",
            "3. Keep them calm and avoid giving food or drink.",
            "4. Seek medical help immediately."
        )
        "Spinal Injury" -> listOf(
            "1. Keep the person still to avoid further injury.",
            "2. If the person is conscious, reassure them to stay calm.",
            "3. Call emergency services for immediate medical intervention.",
            "4. Do not attempt to move the person unless there is immediate danger (e.g., fire)."
        )
        "Sprains & Strains" -> listOf(
            "1. Rest the injured area and avoid putting weight on it.",
            "2. Apply ice to reduce swelling for 20 minutes at a time.",
            "3. Compress the area with an elastic bandage.",
            "4. Elevate the injured area above the heart level.",
            "5. Seek medical attention if pain or swelling persists."
        )
        "Stroke" -> listOf(
            "1. Recognize symptoms: sudden numbness, difficulty speaking, confusion, dizziness.",
            "2. Call emergency services immediately.",
            "3. Do not give the person food or drink.",
            "4. Keep the person calm and still while waiting for emergency help."
        )
        "Tick Bites" -> listOf(
            "1. Remove the tick as soon as possible using fine-tipped tweezers.",
            "2. Grasp the tick as close to the skin as possible and pull it out gently without squeezing.",
            "3. Clean the bite area with alcohol or soap and water.",
            "4. Monitor for symptoms of Lyme disease or other infections.",
            "5. Seek medical help if any symptoms arise."
        )




        else -> listOf("Instructions for $topic are not available.")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Steps for $topic", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) { // Navigates back when clicked
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF111827)) // Dark top bar
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF111827)) // Dark background for the body
                .padding(16.dp)
        ) {
            // Display the steps for the selected topic
            steps.forEach { step ->
                Text(
                    text = step,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}