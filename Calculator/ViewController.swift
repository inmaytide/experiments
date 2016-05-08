//
//  ViewController.swift
//  Calculator
//
//  Created by Moss on 5/5/16.
//  Copyright © 2016 Moss. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    @IBOutlet private weak var display: UILabel!
    
    private var userInTheMiddleOfTyping = false
    
    private var brain = CalculatorBrain()
    
    private var saveProgram: CalculatorBrain.PropertyList?
    
    private var displayValue: Double {
        get {
            return Double(display.text!)!
        }
        set {
            display.text = "\(newValue)"
        }
    }
    
    @IBAction func save() {
        saveProgram = brain.program
    }
    
    @IBAction func restore() {
        if saveProgram != nil {
            brain.program = saveProgram!
            displayValue = brain.result
        }
    }
    
    @IBAction private func performOperation(sender: UIButton) {
        if userInTheMiddleOfTyping {
            brain.setOperand(displayValue)
            userInTheMiddleOfTyping = false
        }
        if let mathematicalSymbol = sender.currentTitle {
            brain.performOperation(mathematicalSymbol)
        }
        displayValue = brain.result
    }
    
    
    @IBAction private func touchDigit(sender: UIButton) {
        let digit = sender.currentTitle!
        if userInTheMiddleOfTyping {
            let textCurrentlyInDisply = display.text!
            display.text = textCurrentlyInDisply + digit
        } else {
            display.text = digit
            userInTheMiddleOfTyping = true
        }
    }
}

